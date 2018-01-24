package rcoleman.rasmussencollege.com.warera;
/*
    @Robert Coleman 11/5/2017
    War Era app______Game Rules Class
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameRules extends AppCompatActivity
{
    // variables for the buttons!
    private static ImageButton MENU;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);
        MENU();
    }

    // Go to main menu function
    private void MENU()
    {
        MENU = (ImageButton)findViewById(R.id.menuButton);

        MENU.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent myIntent = new Intent(GameRules.this, MainMenu.class);
                                        GameRules.this.startActivity(myIntent);

                                    }
                                }
        );
    }
}
