package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameChooserActivity extends AppCompatActivity {

    List<String> testAdapterList;
    Spinner testChooser;
    ArrayAdapter<String> testAdapter;
    Menu mMenu;
    RelativeLayout logInlayout;
    AnimationDrawable animationDrawable;

    public static String chosen_test = "";
    private final String MENU_NUM = "MENUNUM";



    DatabaseManager getMyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_chooser);

        logInlayout = findViewById(R.id.gameChooseLayout);
        animationDrawable = (AnimationDrawable) logInlayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        getMyDB = new DatabaseManager(this);

        List<String> testNamesForAdapter =  getMyDB.getAllTestNames();


        testChooser = findViewById(R.id.testChooser);

        testAdapterList = new ArrayList<>();

        testAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testAdapterList );

        testChooser.setAdapter(testAdapter);

        for(int i = 0; i<testNamesForAdapter.size(); i++)
        {
            testAdapter.add(testNamesForAdapter.get(i));
            testAdapter.notifyDataSetChanged();

        }



        testChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                chosen_test = testChooser.getSelectedItem().toString();
//                selectedTest = testMenu.getSelectedItem().toString();
//                QuestionBag returnedTest = getDB.getTest(selectedTest);

                try
                {
//                    if (!(returnedTest.getQuestion(0).getQuestion().equals(itemsAdapter.getItem(0)))) {
//                        toastMessage("You've selected " + selectedTest);
//
//                        //CLEARS WHATS INSIDE THE CURRENT ADAPTER SO IT DOESN'T APPEND TO THE PREVIOUS QUESTIONS
//                        itemsAdapter.clear();
//                        //REPOPULATES THE ADAPTER WITH THE SELECTED TEST
//                        for (int i = 0; i < returnedTest.bagSize(); i++) {
//
//                            itemsAdapter.add(returnedTest.getQuestion(i).getQuestion());
//                        }
//                    }
                }

                //CATCHES ArrayIndexOutOfBoundException WHEN ITEMSADAPTER HAS A SIZE OF 0
                catch(Exception e)
                {
//                    if(returnedTest.bagSize()== 0)
//                    {
//                        itemsAdapter.clear();
//                    }
//
//                    else {
//                        toastMessage(e.toString());
//                        //POPULATES THE ITEMSADAPTER WITH THE FIRST TEST IN THE SPINNER
//                        for (int i = 0; i < returnedTest.bagSize(); i++) {
//
//                            itemsAdapter.add(returnedTest.getQuestion(i).getQuestion());
//                        }
//                    }

                }
//


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

    public void launchTicTacToe(View view)
    {
        Intent launchRaceGame = new Intent(this, TicTacToe_Activity.class);
        startActivity(launchRaceGame);

    }


    public void launchRaceGame(View view)
    {
        Intent launchRaceGame = new Intent(this, RaceGameActivity.class);
        startActivity(launchRaceGame);

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
                myIntent.putExtra(MENU_NUM,"2");
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void toastMessage(String message)
    {
        Toast toast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }
}
