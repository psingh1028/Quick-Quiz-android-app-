package com.zybooks.studyhelpergame;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
    UserAndQuesDatabaseManager udb;
    private ArrayList<User> uArr;
    private String user1Name;
    private String user2Name;
    private final String USER_ONE = "USER1";
    private final String USER_TWO = "USER2";
    public static User finalUser1;
    public static User finalUser2;
    private Menu mMenu;

    TextView playingView;

    TableLayout myTableLayout;
    AnimationDrawable animationDrawable;
    private final String MENU_NUM = "MENUNUM";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        myTableLayout = findViewById(R.id.myLayout);
        animationDrawable = (AnimationDrawable) myTableLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        udb =  new UserAndQuesDatabaseManager(getApplicationContext());
        uArr = udb.getAllUsers();

        Intent intent = getIntent();
        user1Name = intent.getStringExtra(USER_ONE);
        user2Name = intent.getStringExtra(USER_TWO);
        finalUser1 = retrunUser(user1Name);
        finalUser2 = retrunUser(user2Name);
        playingView = findViewById(R.id.PlayersTXT);

        playingView.setText("Player 1: " + finalUser1.getUsername()+ "\nPlayer 2: "+finalUser2.getUsername());

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
                myIntent.putExtra(MENU_NUM,"0");
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startTest(View view)
    {
        //  startTestsActivity();
        Intent testActivity= new Intent(this, AddQuestionActivity.class);
        startActivity(testActivity);
    }


    public void startStatsActivity(View view)
    {
        Intent testActivity= new Intent(this, StatsActivity.class);
        startActivity(testActivity);
    }

    public void startGames(View view)
    {
        Intent startGames = new Intent(this, GameChooserActivity.class);
        startActivity(startGames);

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

    public void logOutOnCLick(View view)
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

    public void flashBtnClick(View view)
    {
        Intent myIntent = new Intent(this, FlashCardActivity.class);
        this.startActivity(myIntent);
    }
}
