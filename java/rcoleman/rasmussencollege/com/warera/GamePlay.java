package rcoleman.rasmussencollege.com.warera;
/*
    @Robert Coleman  updated on 12/8/2017
    War Era app______Game Play Class
 */
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
// for text animation
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.duration;

public class GamePlay extends AppCompatActivity
{
    // Global variables
    private static ImageButton MENU;
    private static final Random rand = new Random(); // for random generator!
    private TextView PlayerStrategy;
    private TextView AIStrategy;
    private TextView PlayerPoints;
    private TextView AIPoints;
    private int points;
    private int AI_points;
    private int TheZones;
    private int wins;
    private int AIwins;
    private FrameLayout frame;
    public  TextView GameUser;
    private TextView Zones;
    private TextView ZonesWon;

    // Frame animation variable!
    Animation animSlideIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        // Calling functions
        MENU();
        PLAYERFUNCTIONS();// Aka game engine!
        // Getting the game user's name from the login screen (sharing public data set!).
        GameUser = (TextView) findViewById(R.id.textViewUser);
        Intent intent = getIntent();
        String GamerName = intent.getStringExtra("user");
        GameUser.setText("User: " + GamerName);
    }
    // Function that controls the player/user of the application
    private void PLAYERFUNCTIONS()
    {

        int randInt = rand.nextInt(3)+1;// generating random value

        PlayerStrategy = (TextView) findViewById(R.id.userStrategy);// text
        PlayerPoints = (TextView) findViewById(R.id.userPoints);
        ImageView img= (ImageView) findViewById(R.id.outputImage);// image

        // Making fame animation
        frame=(FrameLayout)findViewById(R.id.the_Frame);
        animSlideIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);// calling animation xml file from anim folder!
        frame.startAnimation(animSlideIn);

        // re-evaluate random generation and display images for output
        if (randInt == 3)
        {
            randInt = rand.nextInt(3)+1;// running generator one more time
            if (randInt == 3)
            {
                PlayerStrategy.setText(String.valueOf("Heavy Artillery"));
                // Get image to be re-painted!
                img.setImageResource(R.drawable.heavy_artillery);
                points = 500;
                PlayerPoints.setText(String.valueOf("User Points: " + points));
            }
        }
        if (randInt == 2)
        {
            PlayerStrategy.setText(String.valueOf("Light Artillery"));
            // Get image to be re-painted!
            img.setImageResource(R.drawable.lightweight_artillery);
            points = 200;
            PlayerPoints.setText(String.valueOf("User Points: " + points));
        }
        if (randInt == 1)
        {
            PlayerStrategy.setText(String.valueOf("The Hero"));
            // Get image to be re-painted!
            img.setImageResource(R.drawable.hero);
            points = 100;
            PlayerPoints.setText(String.valueOf("User Points: " + points));
        }
        //+++++++++++++++++++++++++++++++++++++Player/User action buttons!

            Button button1 = (Button) findViewById(R.id.radioButton1);
            button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Calling the AI function and re-calling player function!
                theAI();
                PLAYERFUNCTIONS();
                points = points + 13;
                PlayerPoints.setText(String.valueOf("User Points: " + points));

                TheZones ++;
                Zones = (TextView) findViewById(R.id.textZones);
                Zones.setText(String.valueOf("Zones: " + TheZones));
                ScorePoints(); // Call the function in charge of keeping the points score board

                }
            });
            Button button2 = (Button) findViewById(R.id.radioButton2);
            button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                theAI();
                PLAYERFUNCTIONS();
                points = points + 15;
                PlayerPoints.setText(String.valueOf("User Points: " + points));

                TheZones ++;
                Zones = (TextView) findViewById(R.id.textZones);
                Zones.setText(String.valueOf("Zones: " + TheZones));
                ScorePoints();

                }
            });
            Button button3 = (Button) findViewById(R.id.radioButton3);
            button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                theAI();
                PLAYERFUNCTIONS();
                points = points + 11;
                PlayerPoints.setText(String.valueOf("User Points: " + points));

                TheZones ++;
                Zones = (TextView) findViewById(R.id.textZones);
                Zones.setText(String.valueOf("Zones: " + TheZones));
                ScorePoints();

                }
            });
    }
    //++++++++++++++++++++++++++++++++++++

    // Keep out with the points functions
    private void ScorePoints()
    {
        // Adding the zones won by the player/user
        int theUserpoints = Integer.parseInt(String.valueOf(points)); // parsing values for calculation
        int theAIpoints = Integer.parseInt(String.valueOf(AI_points));
        if (theUserpoints > theAIpoints)
        {
            wins ++;// Adding up zones won by the player/user
            ZonesWon = (TextView) findViewById(R.id.zoneWins);
            ZonesWon.setText(String.valueOf("Zones Won: " + wins));
        }
        else
        {
            AIwins ++;// Adding up the zones won by the AI
            Context aiwintext = getApplicationContext();
            CharSequence text = "AI won the zone";
            int duration = Toast.LENGTH_SHORT;
            // The AI won a zone message
            Toast toast = Toast.makeText(aiwintext, text, duration);
            toast.show();
        }

        //Finish Game once zone 9 is active
      if(TheZones == 9)
      {
          /*
             Using the endgame.xml to display custom dialog box message!
             The message displays the total score of the player/user
             and the AI.
          */
          // Create custom dialog object
          Dialog dialog = new Dialog(GamePlay.this);
          // Include dialog.xml file
          dialog.setContentView(R.layout.endgame);// get the endgame.xml that has attributes for dialog box

          TextView GAMEOtext = (TextView) dialog.findViewById(R.id.endGameText);
          GAMEOtext.setText("GAME OVER! \n" + "YOUR TOTAL SCORE: " + wins + "\n" +
                  "AI TOTAL SCORE: " + AIwins);
          dialog.show();

          Button okButton = (Button) dialog.findViewById(R.id.ok);
          // if ok button is clicked, close the custom dialog and go back to the game's main menu
          okButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v)
              {
                  // Once message is display and button click game goes back to the main screen
                  Intent myIntent = new Intent(GamePlay.this, MainMenu.class);
                  GamePlay.this.startActivity(myIntent);
              }
          });

      }
    }


    // AI function
    private void theAI()
    {
        int randInt2 = rand.nextInt(3)+1;// Generating random value

        AIStrategy = (TextView) findViewById(R.id.aiStrategy);// text
        AIPoints = (TextView) findViewById(R.id.aiPoints);
        ImageView img= (ImageView) findViewById(R.id.outputImage2);// image

        // Making fame animation
        frame=(FrameLayout)findViewById(R.id.the_Frame);
        animSlideIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);// calling animation xml file from anim folder!
        frame.startAnimation(animSlideIn);

        // re-evaluate random generation and display images for output
        if (randInt2 == 3)
        {
            randInt2 = rand.nextInt(3)+1;// running generator one more time
            if (randInt2 == 3)
            {
                AIStrategy.setText(String.valueOf("Heavy Artillery"));
                // Get image to be re-painted!
                img.setImageResource(R.drawable.heavy_artillery);
                AI_points = 500;
                AIPoints.setText(String.valueOf("User Points: " + AI_points));
            }
        }
        if (randInt2 == 2)
        {
            AIStrategy.setText(String.valueOf("Light Artillery"));
            // Get image to be re-painted!
            img.setImageResource(R.drawable.lightweight_artillery);
            AI_points = 200;
            AIPoints.setText(String.valueOf("AI Points: " + AI_points));
        }
        if (randInt2 == 1)
        {
            AIStrategy.setText(String.valueOf("The Hero"));
            // Get image to be re-painted!
            img.setImageResource(R.drawable.hero);
            AI_points = 100;
            AIPoints.setText(String.valueOf("AI Points: " + AI_points));
        }
        // generating random value for the AI actions
        int aiAction = rand.nextInt(3)+1;
        if (aiAction == 1)
        {
            AI_points = AI_points + 13;
            AIPoints.setText(String.valueOf("AI Points: " + AI_points));
        }
        if (aiAction == 2)
        {
            AI_points = AI_points + 15;
            AIPoints.setText(String.valueOf("AI Points: " + AI_points));
        }
        if (aiAction == 3)
        {
            AI_points = AI_points + 11;
            AIPoints.setText(String.valueOf("AI Points: " + AI_points));
        }

    }
    // Go to main menu function
    private void MENU()
    {
        MENU = (ImageButton)findViewById(R.id.menuButton);

        MENU.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent myIntent = new Intent(GamePlay.this, MainMenu.class);
                                        GamePlay.this.startActivity(myIntent);

                                    }
                                }
        );
    }
}
