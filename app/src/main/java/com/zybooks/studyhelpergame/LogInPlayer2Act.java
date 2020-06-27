package com.zybooks.studyhelpergame;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LogInPlayer2Act extends AppCompatActivity {

    private UserAndQuesDatabaseManager udb;
    private EditText inputTxt;
    private TextView outputTxt;
    private ArrayList<User> uArr;
    private String user1Name;
    private String user2Name;
    private final String USER_ONE = "USER1";
    private final String USER_TWO = "USER2";


    TableLayout logInlayout2;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_player2);

        logInlayout2 = findViewById(R.id.secondLoginTableLayout);
//        myTableLayout = findViewById(R.id.mainActLayout);
        animationDrawable = (AnimationDrawable) logInlayout2.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();



        udb = new UserAndQuesDatabaseManager(getApplicationContext());
        inputTxt = findViewById(R.id.userNameField);
        outputTxt = findViewById(R.id.outPutText);
        uArr = new ArrayList<>();
        uArr = udb.getAllUsers();
        Intent intent = getIntent();
        user1Name = intent.getStringExtra(USER_ONE);
        user2Name = "";

    }

    public void LogInBtn(View view)
    {
        user2Name = inputTxt.getText().toString();
        user2Name.trim().toLowerCase();
        user2Name = user2Name.trim().toLowerCase();
        uArr = udb.getAllUsers();
        if(isInDB(user2Name))
        {
            if(user2Name.equals(user1Name))
            {
                outputTxt.setText("That's Player 1");
            }
            else
            {
                //This would go into the main activity
                //Puts the two usernames in an intent and is travels throughout the program
                //Once we get to the main activity we can do this and use it throughout instead of using intents
                //public static User finalUser1;
                //public static User finalUser2;
                //We just need to initialize it in the main menu.
                //We would just search the database and the search would return a User if found and yeah.


                Intent myIntent = new Intent(this, MainMenuActivity.class);
                myIntent.putExtra(USER_ONE,user1Name);
                myIntent.putExtra(USER_TWO,user2Name);
                this.startActivity(myIntent);
            }

        }
        else
        {
            outputTxt.setText("User Not Found");
        }
    }

    public void CreateBtn(View view)
    {
        user2Name = inputTxt.getText().toString();
        user2Name = user2Name.trim().toLowerCase();
        uArr = udb.getAllUsers();
        if(isInDB(user2Name))
        {
            outputTxt.setText("Enter A Different Username");
        }
        else
        {
            User x = new User();
            x.setUsername(user2Name);
            x.setQuesRight(0);
            x.setQuesWrong(0);
            udb.insertUser(x);

//            This would go into the main activity also.

//            Intent myIntent = new Intent(this, MainActivity.class);
////            myIntent.putExtra(USER_ONE,user1Name);
////            myIntent.putExtra(USER_TWO,uName);
////            this.startActivity(myIntent);

            Intent myIntent = new Intent(this, MainMenuActivity.class);
            myIntent.putExtra(USER_ONE,user1Name);
            myIntent.putExtra(USER_TWO,user2Name);
            this.startActivity(myIntent);
        }
    }

    public boolean isInDB(String x)
    {

        if (x != "") {
            for (int i = 0; (i < uArr.size()); i++) {
                if (x.equals(uArr.get(i).getUsername())) {
                    return true;
                }
            }
        }

        return false;
    }

    public User retrunUser(String x)
    {

        if (x != "") {
            for (int i = 0; (i < uArr.size()); i++) {
                if (x.equals(uArr.get(i).getUsername())) {
                    return uArr.get(i);
                }
            }
        }

        return null;
    }

}


