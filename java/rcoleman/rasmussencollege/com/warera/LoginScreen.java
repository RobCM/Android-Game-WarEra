package rcoleman.rasmussencollege.com.warera;
/*
    @Robert Coleman  updated on 11/24/2017
    War Era app______Login Class
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity
{
    // Global variables
    private static ImageButton MENU;
    private EditText mUserNameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        MENU();
        // Set up the login form. Calling text inputs from XML file!
        mUserNameView = (EditText) findViewById(R.id.userName);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login || id == EditorInfo.IME_NULL)
                {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptLogin();
            }
        });

        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        //Here we will validate saved preferences
        if (!new PrefManager(this).isUserLogedOut())
        {
            //user's email and password both are saved in preferences
            startGameActivity();
        }

    }

    // Go to main menu function
    private void MENU()
    {
        MENU = (ImageButton)findViewById(R.id.menuButton);

        MENU.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent myIntent = new Intent(LoginScreen.this, MainMenu.class);
                                        LoginScreen.this.startActivity(myIntent);

                                    }
                                }
        );
    }

    /*
     Attempts to sign in or register the account specified by the login form.
     If there are form errors (invalid email, missing fields, etc.), the
     errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String user = mUserNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email))
        {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email))
        {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // save data in local shared preferences
            if (checkBoxRememberMe.isChecked())
                saveLoginDetails(email, password, user);
            startGameActivity();
        }
    }

    private void startGameActivity()
    {
        Intent intent = new Intent(this, GamePlay.class); // Everything good go to play game screen!
        intent.putExtra("user",mUserNameView.getText().toString());// push user name to be display on the Game Play screen
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password, String user)
    {
        new PrefManager(this).saveLoginDetails(email, password, user);// Calling shared preference manager class!
    }

    private boolean isEmailValid(String email)
    {
        return email.contains("@"); // making sure email string contains @ character!
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() >= 4; // making sure that the password used is at least 4 characters
    }
}

