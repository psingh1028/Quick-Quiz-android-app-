package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SampleMainMeny extends AppCompatActivity {

    private UserAndQuesDatabaseManager udb;
    private String user1Name;
    private String user2Name;
    private Menu mMenu;
    private ArrayList<User> uArr;
    private final String USER_ONE = "USER1";
    private final String USER_TWO = "USER2";
    public static User finalUser1;
    public static User finalUser2;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_main_meny);
        udb =  new UserAndQuesDatabaseManager(getApplicationContext());
        uArr = udb.getAllUsers();
        tv = findViewById(R.id.test);
        Intent intent = getIntent();
        user1Name = intent.getStringExtra(USER_ONE);
        user2Name = intent.getStringExtra(USER_TWO);
        finalUser1 = retrunUser(user1Name);
        finalUser2 = retrunUser(user2Name);
        String test = "";

        test+="Players\n" + "Player 1: " +finalUser1.getUsername()+"\nPlayer 2: "+finalUser2.getUsername()+"\n";
        tv.setText(test);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine which menu option was chosen
        // Should be in the main menu
        switch (item.getItemId()) {
            case R.id.action_Help:
                Intent myIntent = new Intent(this, HelpTextActivity.class);
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




/*
                to set the questions right and wrong you would implement the database
                and use
                public static User finalUser1;
                public static User finalUser2;

                so we would get the questions right and wrong store it in a temp int and then just ++ it
                then just do finalUser1.setQuesRight(temp int);
                then you would just update that user in the database by
                udb.updateUser(finalUser1);
 */

    public void statsAct(View view)
    {
        Intent myIntent = new Intent(this, StatsActivity.class);

        this.startActivity(myIntent);

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
