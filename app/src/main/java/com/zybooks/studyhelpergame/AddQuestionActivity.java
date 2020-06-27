package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity implements ClassInfoDialog.ClassInfoDialogListener {

    private DatabaseManager getDB;

    Button addTest;
    EditText testName;

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    String itemText;

    String newQuestion;
    String questionAnswer;

    Spinner testMenu;
    List<String> testAdapterList;
    List<QuestionBag> listOfTest;
    ArrayAdapter<String> testAdapter;

    public static String test = " ";

    String selectedTest;
    Menu mMenu;
    private final String MENU_NUM = "MENUNUM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        addTest = findViewById(R.id.createTestButton);
        lvItems = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        testName = findViewById(R.id.testName);
        testMenu = findViewById(R.id.testSpinner);
        testAdapterList = new ArrayList<>();

        listOfTest = new ArrayList<>();
        testAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testAdapterList );

        testAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        testMenu.setAdapter(testAdapter);

        getDB = new DatabaseManager(this);
        setupListViewListener();

        try
        {
            //GETS ALL DISTINCT TEST NAMES FROM DATABASE
           List<String> testNamesForAdapter =  getDB.getAllTestNames();

           //POPULATES THE SPINNER WITH THE TEST NAMES
           for(int i = 0; i<testNamesForAdapter.size(); i++)
           {
               testAdapter.add(testNamesForAdapter.get(i));
               testAdapter.notifyDataSetChanged();

           }

        }

        catch(Exception E)
        {

        }

        testMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedTest = testMenu.getSelectedItem().toString();
                QuestionBag returnedTest = getDB.getTest(selectedTest);


                try
                {
                if (!(returnedTest.getQuestion(0).getQuestion().equals(itemsAdapter.getItem(0)))) {
                    toastMessage("You've selected " + selectedTest);

                    //CLEARS WHATS INSIDE THE CURRENT ADAPTER SO IT DOESN'T APPEND TO THE PREVIOUS QUESTIONS
                    itemsAdapter.clear();
                    //REPOPULATES THE ADAPTER WITH THE SELECTED TEST
                    for (int i = 0; i < returnedTest.bagSize(); i++) {

                        itemsAdapter.add(returnedTest.getQuestion(i).getQuestion());
                    }
                }
            }

            //CATCHES ArrayIndexOutOfBoundException WHEN ITEMSADAPTER HAS A SIZE OF 0
            catch(Exception e)
            {
                if(returnedTest.bagSize()== 0)
                {
                    itemsAdapter.clear();
                }

                else {
                    toastMessage(e.toString());
                    //POPULATES THE ITEMSADAPTER WITH THE FIRST TEST IN THE SPINNER
                    for (int i = 0; i < returnedTest.bagSize(); i++) {

                        itemsAdapter.add(returnedTest.getQuestion(i).getQuestion());
                    }
                }

            }
//


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    public void createTest(View view)
    {
        //Gets the String value of what the user enters in the EditText (Test Name)
        String testNameString = testName.getText().toString();

        //Creates a new QuestionBag variable which contains an arrayList of Questions and adds it to listOfTest arrayList
        QuestionBag newTest = new QuestionBag(testNameString);
        listOfTest.add(newTest);
        //Updates the Spinner and appends the new test name
        testAdapter.add(testNameString);
        testAdapter.notifyDataSetChanged();


    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override

                    //When the user clicks on the item for long enough we pass the information of the class into the display activity and display each one
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        getDB.deleteQuestion(selectedTest, itemsAdapter.getItem(pos));
                        itemsAdapter.remove(itemsAdapter.getItem(pos));
                        return true;
                    }

                });
    }


    public void addQuestion(View view)
    {
        openDialog();



    }



    public QuestionBag getTest(String testName)
    {
        for(int i = 0; i<listOfTest.size(); i ++)
        {
            QuestionBag foundTest = listOfTest.get(i);
            if(foundTest.getBagName().equals(testName))
            {
                return foundTest;
            }
        }


        return null;
    }




    public void toastMessage(String message)
    {
        Toast toast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openDialog()
    {

        ClassInfoDialog infoDialog = new ClassInfoDialog();

        infoDialog.show(getSupportFragmentManager(), "info Dialog");



    }

    @Override
    public void applyTexts(String newQuestion, String questionAnswer)
    {
        this.newQuestion =  newQuestion;
        this.questionAnswer = questionAnswer;
        Question questionToAdd = new Question(this.newQuestion, this.questionAnswer);
        QuestionBag currentTest = getDB.getTest(selectedTest);
        currentTest.add(questionToAdd);
        getDB.insert(selectedTest, questionToAdd.getQuestion(), questionToAdd.getcAnswer());
        itemsAdapter.add(this.newQuestion);

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
                myIntent.putExtra(MENU_NUM,"1");
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
