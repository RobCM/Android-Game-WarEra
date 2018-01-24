package rcoleman.rasmussencollege.com.warera;
/*
    @Robert Coleman  updated on 11/24/2017
    War Era app______PrefManager Class
        shared preference manager for  Private and one Shared preference set!
 */
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager
{
    Context context;

    PrefManager(Context context)
    {
        this.context = context;
    }
    // PRIVATE! Shared preferences
    public void saveLoginDetails(String user, String email, String password)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", user);
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }
    // Once the user is out of the application!
    public boolean isUserLogedOut()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isNameEmpty = sharedPreferences.getString("userName", "").isEmpty();
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
}

