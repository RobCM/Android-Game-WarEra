package rcoleman.rasmussencollege.com.warera;
/*
    @Robert Coleman 11/4/2017
    War Era app______Main Menu Class
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity
{
    // variables for the buttons!
    private static ImageButton gamePlay;
    private static ImageButton gameRules;
    private static ImageButton gameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        // Calling functions
        gamePlay();
        gameRules();
        gameInfo();

    }
    // Go to game play screen function
    private void gamePlay()
    {
        gamePlay = (ImageButton)findViewById(R.id.imageButton1);

        gamePlay.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Intent myIntent = new Intent(MainMenu.this, LoginScreen.class);
                                             MainMenu.this.startActivity(myIntent);

                                         }
                                     }
        );
    }

    // Go to game rules screen function
    private void gameRules()
    {
        gameRules = (ImageButton)findViewById(R.id.imageButton2);

        gameRules.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent myIntent = new Intent(MainMenu.this, GameRules.class);
                                            MainMenu.this.startActivity(myIntent);

                                        }
                                    }
        );
    }

    // Go to game info screen function
    public void gameInfo()
    {
        gameInfo = (ImageButton)findViewById(R.id.imageButton3);

        gameInfo.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                                  Intent myIntent = new Intent(MainMenu.this, GameInfo.class);
                                                  MainMenu.this.startActivity(myIntent);

                                              }
                                          }
        );
    }
}
