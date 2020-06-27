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
public class LogInActivity extends AppCompatActivity {

    private UserAndQuesDatabaseManager udb;
    private EditText inputTxt;
    private TextView outputTxt;
    private ArrayList<User> uArr;

    private final String USER_ONE = "USER1";

    TableLayout logInlayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInlayout = findViewById(R.id.logInTableLayout);
//        myTableLayout = findViewById(R.id.mainActLayout);
        animationDrawable = (AnimationDrawable) logInlayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        udb = new UserAndQuesDatabaseManager(getApplicationContext());
        inputTxt = findViewById(R.id.userNameField);
        outputTxt = findViewById(R.id.outPutText);
        uArr = new ArrayList<>();
        uArr = udb.getAllUsers();


    }

    public void LogInBtn(View view)
    {
        String uName = inputTxt.getText().toString();
        uName = uName.trim().toLowerCase();
        uArr = udb.getAllUsers();
        if(isInDB(uName))
        {
            Intent myIntent = new Intent(this, LogInPlayer2Act.class);
            myIntent.putExtra(USER_ONE,uName);
            this.startActivity(myIntent);
        }
        else
        {
            outputTxt.setText("User Not Found");
        }
    }

    public void CreateBtn(View view)
    {
        String uName = inputTxt.getText().toString();
        uName=uName.trim().toLowerCase();
        uArr = udb.getAllUsers();
        if(isInDB(uName))
        {
            outputTxt.setText("Enter A Different Username");
        }
        else
        {
            User x = new User();
            x.setUsername(uName);
            x.setQuesRight(0);
            x.setQuesWrong(0);
            udb.insertUser(x);
            Intent myIntent = new Intent(this, LogInPlayer2Act.class);
            myIntent.putExtra(USER_ONE,x.getUsername());
            this.startActivity(myIntent);
            //add and switch

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

}
