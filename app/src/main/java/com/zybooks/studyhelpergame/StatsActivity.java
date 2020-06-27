package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class StatsActivity extends AppCompatActivity {

    private TextView statsTexts;
    private UserAndQuesDatabaseManager udb;
    private ArrayList<User> aList;
    private String user1Name;
    private String user2Name;
    private final String USER_ONE = "USER1";
    private final String USER_TWO = "USER2";
    private Menu mMenu;
    private User u1;
    private User u2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        statsTexts = findViewById(R.id.outPutText);
        statsTexts.setMovementMethod(new ScrollingMovementMethod());
        udb = new UserAndQuesDatabaseManager(getApplicationContext());
        aList = new ArrayList<>();
        Intent intent = getIntent();
        user1Name = intent.getStringExtra(USER_ONE);
        user2Name = intent.getStringExtra(USER_TWO);
        populateEditText();
    }



    public void populateEditText()
    {
        aList=  udb.getAllUsers();

        String allStats = "";

        for (int i = 0; i<aList.size(); i++)
        {
            allStats+=aList.get(i).toString()+"\n";
        }
        statsTexts.setText(allStats);

    }

    public void sortLtoH()
    {
        aList=  udb.getAllUsers();

        String allStats = "";
        Collections.sort(aList, new lowToHigh());
        for (int i = 0; i<aList.size(); i++)
        {
            allStats+=aList.get(i).toString()+"\n";
        }
        statsTexts.setText(allStats);


    }

    public void sortHtoL()
    {
        aList=  udb.getAllUsers();

        String allStats = "";
        Collections.sort(aList, new highToLow());
        for (int i = 0; i<aList.size(); i++)
        {
            allStats+=aList.get(i).toString()+"\n";
        }
        statsTexts.setText(allStats);


    }


    public void sortBtn1(View view)
    {
        sortHtoL();
    }

    public void sortBtn2(View view)
    {
        sortLtoH();
    }



}
