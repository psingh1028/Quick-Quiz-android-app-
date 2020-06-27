package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

import java.util.ArrayList;

public class  TicTacToe_Activity extends AppCompatActivity implements View.OnClickListener{

    public TextView [][] textViews = new TextView[3][3];
    public RadioButton [] RadioButtons = new RadioButton[4];
    public TextView questionTV;
    RandomizeQuestions RD;
    private UserAndQuesDatabaseManager udb;
    DatabaseManager DB;
    ArrayList<Question> askedQuestionArrayList = new ArrayList<>();
    String correctAnswer;
    String random1,random2,random3,random4;
    Button submitButton;
    TextView selected;
    boolean player1turn=true;
    TextView player1turnTV;
    TextView player2turnTV;
    RadioGroup radio_group;
    Question Question2bAsked;
    int x;
    Intent myintent;
    boolean checkifRight;
    Menu mMenu;
    private final String MENU_NUM = "MENUNUM";



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_);
        DB = new DatabaseManager(this);
        myintent = new Intent(this,MainActivityAnim.class);
        checkifRight=true;
        RD = new RandomizeQuestions(DB.getTest(GameChooserActivity.chosen_test));
        questionTV = findViewById(R.id.questionDisplayTV);
        submitButton = findViewById(R.id.submitButton);
        player1turnTV = findViewById(R.id.Player1TurnTV);
        player2turnTV = findViewById(R.id.Player2TurnTV);

        radio_group = findViewById(R.id.radio_group);
        checkForPlayerTurn();
        x=0;
        udb = new UserAndQuesDatabaseManager(getApplicationContext());
        PopulateRadioButton();

        for(int i =0;i<3;i++)//this initializes the 9 text views into a 2D textViews array
        {
            for(int j = 0;j<3;j++)
            {
                String TVid = "TV"+i+j;
                int resId = getResources().getIdentifier(TVid,"id",getPackageName());
                textViews[i][j]=findViewById(resId);
                textViews[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Question2bAsked = RD.getRandomQuestion();
        while (checkIfAlreadyAsked(Question2bAsked)) //this check to see if this question has already been asked if it has ask another question other wise put it into the array list
        {
            Question2bAsked = RD.getRandomQuestion();
        }
//        if(checkifRight==true) {
//            askedQuestionArrayList.add(Question2bAsked);
//        }

        printQuestionAskedarraylist();

        Question QuestionRandom1 = RD.getRandomQuestion() ;Question QuestionRandom2 = RD.getRandomQuestion(); Question QuestionRandom3 = RD.getRandomQuestion();

        questionTV.setText(Question2bAsked.getQuestion());
        correctAnswer = Question2bAsked.getcAnswer();
        selected = findViewById(v.getId());//this gets the selected textView
        PopulateRadioButton();



        while(checkifSame(Question2bAsked,QuestionRandom1,QuestionRandom2,QuestionRandom3)==true)// this makes sure that the question asked doesn't appear more than--
        {                                                                                       //  -- once in the multiple choice.
            QuestionRandom1 = RD.getRandomQuestion();QuestionRandom2 = RD.getRandomQuestion();QuestionRandom3 = RD.getRandomQuestion();
        }

        ArrayList<String> randArray = new ArrayList<>();//makes new array list to insert random number into so numbers can be compared

//lines 84-89 generate 4 random numbers between 1 and 4
        random1 = String.valueOf(generateRandomNum());
        randArray.add(random1);//this lines adds this random # to an array
        random2 = String.valueOf(generateRandomNum());
        random3 = String.valueOf(generateRandomNum());
        random4 = String.valueOf(generateRandomNum());

///lines 91-108 check if the random number generated is already in the array if it is then generate another one but if not then add it to the array--
        //--this is to make sure that the 4 random numbers chosen don't repeat;
        while(checkIfInArray(randArray,random2))
        {
            random2 = String.valueOf(generateRandomNum());
        }
        randArray.add(random2);

        while(checkIfInArray(randArray,random3))
        {
            random3 = String.valueOf(generateRandomNum());
        }
        randArray.add(random3);

        while(checkIfInArray(randArray,random4))
        {
            random4 = String.valueOf(generateRandomNum());
        }
        randArray.add(random4);


        int randomNumber = generateRandomNum();
        String randomNum = String.valueOf(randomNumber);

        Log.d("random Number is ",randomNum);

        RadioButtons[Integer.parseInt(random1)].setText(Question2bAsked.getcAnswer());
        RadioButtons[Integer.parseInt(random2)].setText(QuestionRandom1.getcAnswer());
        RadioButtons[Integer.parseInt(random3)].setText(QuestionRandom2.getcAnswer());
        RadioButtons[Integer.parseInt(random4)].setText(QuestionRandom3.getcAnswer());






    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onSubmitButtonClicked(View view)
    {

        if(RadioButtons[0].isChecked() && RadioButtons[0].getText().equals(correctAnswer ))
        {
            //  Toast.makeText(this,"this is the correct answer",Toast.LENGTH_SHORT).show();
            if(player1turn==true)
            {
                selected.setText("X");
                x = MainMenuActivity.finalUser1.getQuesRight();
                x++;
                MainMenuActivity.finalUser1.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser1);
                askedQuestionArrayList.add(Question2bAsked);
                player1turn=false;
//                checkifRight=true;
            }
            else
            {
                selected.setText("O");
                x = MainMenuActivity.finalUser2.getQuesRight();
                x++;
                MainMenuActivity.finalUser2.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser2);
                player1turn=true;
//                checkifRight=true;
            }
        }

        else if(RadioButtons[1].isChecked() && RadioButtons[1].getText().equals(correctAnswer))
        {
            //   Toast.makeText(this,"this is the correct answer",Toast.LENGTH_SHORT).show();
            if(player1turn==true)
            {
                selected.setText("X");
                x = MainMenuActivity.finalUser1.getQuesRight();
                x++;
                MainMenuActivity.finalUser1.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser1);
                askedQuestionArrayList.add(Question2bAsked);
                player1turn=false;
//                checkifRight=true;
            }
            else// if(player1turn==false);
            {
                selected.setText("O");
                x = MainMenuActivity.finalUser2.getQuesRight();
                x++;
                MainMenuActivity.finalUser2.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser2);
                askedQuestionArrayList.add(Question2bAsked);
                player1turn=true;
//                checkifRight=true;
            }
        }
        else if(RadioButtons[2].isChecked()&&RadioButtons[2].getText().equals(correctAnswer))
        {
            //  Toast.makeText(this,"this is the correct answer",Toast.LENGTH_SHORT).show();
            if(player1turn==true)
            {
                selected.setText("X");
                x = MainMenuActivity.finalUser1.getQuesRight();
                x++;
                MainMenuActivity.finalUser1.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser1);
                askedQuestionArrayList.add(Question2bAsked);
                player1turn=false;
//                checkifRight=true;
            }
            else// if(player1turn==false);
            {
                selected.setText("O");
                x = MainMenuActivity.finalUser2.getQuesRight();
                x++;
                MainMenuActivity.finalUser2.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser2);
                askedQuestionArrayList.add(Question2bAsked);
                player1turn=true;
//                checkifRight=true;
            }
        }
        else if(RadioButtons[3].isChecked()&&RadioButtons[3].getText().equals(correctAnswer))
        {
            // Toast.makeText(this,"this is the correct answer",Toast.LENGTH_SHORT).show();
            if(player1turn==true)
            {
                selected.setText("X");
                x = MainMenuActivity.finalUser1.getQuesRight();
                x++;
                MainMenuActivity.finalUser1.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser1);
                player1turn=false;
               // checkifRight=true;
            }
            else// if(player1turn==false);
            {
                selected.setText("O");
                x = MainMenuActivity.finalUser2.getQuesRight();
                x++;
                MainMenuActivity.finalUser2.setQuesRight(x);
                udb.updateUser(MainMenuActivity.finalUser2);
                player1turn=true;
              //  checkifRight=true;
            }
        }
        else
        {
            // Toast.makeText(this,"this is not the correct answer",Toast.LENGTH_SHORT).show();
            if(player1turn==true)
            {
                x = MainMenuActivity.finalUser1.getQuesWrong();
                x++;
                MainMenuActivity.finalUser1.setQuesWrong(x);
                udb.updateUser(MainMenuActivity.finalUser1);
                checkifRight=false;
                player1turn=false;

            }
            else
            {
                x = MainMenuActivity.finalUser2.getQuesWrong();
                x++;
                MainMenuActivity.finalUser2.setQuesWrong(x);
                udb.updateUser(MainMenuActivity.finalUser2);
                checkifRight=false;
                player1turn=true;
            }
        }





        //  clearRadioButton(RadioButtons);
        radio_group.clearCheck();
        checkForPlayerTurn();
        String empty = " ";
        questionTV.setText(empty);
        checkforWinner();
        clearRadioButton(RadioButtons);


        // board.checkWinner(textViews);


    }

    public boolean checkifSame(Question one, Question two, Question three,Question four)
    {
        if(one.getcAnswer().equals(two.getcAnswer()) || one.getcAnswer().equals(three.getcAnswer()) || two.getcAnswer().equals(three.getcAnswer())
                || one.getcAnswer().equals(four.getcAnswer()) ||  two.getcAnswer().equals(four.getcAnswer()) || three.getcAnswer().equals(four.getcAnswer()))
        {
            // Toast.makeText(this, "two questions had the same answer", Toast.LENGTH_SHORT).show();
            return true;
        }return false;
    }

    public int generateRandomNum()
    {
        Random rand = new Random();

        int randomNumber = rand.nextInt(4);
        return randomNumber;
    }

    public boolean checkIfInArray(ArrayList list ,String number)
    {

        for(int i = 0;i<list.size();i++)
        {
            if(number.equals(list.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public void clearRadioButton(RadioButton radioButtonArray[])
    {
        for(int i =0;i<4;i++)
        {
            radioButtonArray[i].setText("");
            radioButtonArray[i].setHint("");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void checkForPlayerTurn()
    {
        if(player1turn ==false)
        {
            player2turnTV.setBackground(getDrawable(R.drawable.p2textview_border));
            player1turnTV.setBackground(null);
            player2turnTV.setTextColor(getResources().getColor(R.color.Red));
            player1turnTV.setTextColor(getResources().getColor(R.color.Black));
        }
        else
        {
            player1turnTV.setBackground(getDrawable(R.drawable.p1textview_border));
            player2turnTV.setBackground(null);
            player1turnTV.setTextColor(getResources().getColor(R.color.Blue));
            player2turnTV.setTextColor(getResources().getColor(R.color.Black));

        }
    }

    public void PopulateRadioButton()
    {
        for(int i =0;i<4;i++)//this code initializes the 4 radio button into a radio button array
        {
            int num =i+1;
            String RBid = "RDB"+num;
            int resID = getResources().getIdentifier(RBid,"id",getPackageName());
            RadioButtons[i] = findViewById(resID);
        }
    }

    public void checkforWinner() {
        //checks to see if the left columns is filled with either all x's or all o's
        if (textViews[0][0].getText().equals("X") && textViews[1][0].getText().equals("X") && textViews[2][0].getText().equals("X")) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[0][0].getText().equals("O") && textViews[1][0].getText().equals("O") && textViews[2][0].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }

        //checks to see if middle column is either all x's or o's
        if (textViews[0][1].getText().equals("X") && textViews[1][1].getText().equals("X") && textViews[2][1].getText().equals("X")) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[0][1].getText().equals("O") && textViews[1][1].getText().equals("O") && textViews[2][1].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }

        //checks to see if third column is either all x's or o's
        if (textViews[0][2].getText().equals("X") && textViews[1][2].getText().equals("X") && textViews[2][2].getText().equals("X")) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[0][2].getText().equals("O") && textViews[1][2].getText().equals("O") && textViews[2][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }

        //checks to see if first row is either all x's or o's
        if (textViews[0][0].getText().equals("X") && textViews[0][1].getText().equals("X") && textViews[0][2].getText().equals("X")) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[0][0].getText().equals("O") && textViews[0][1].getText().equals("O") && textViews[0][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }

        //checks to see if second row is eiter all x's or o's
        if (textViews[1][0].getText().equals("X") && textViews[1][1].getText().equals("X") && textViews[1][2].getText().equals("X") ) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[1][0].getText().equals("O") && textViews[1][1].getText().equals("O") && textViews[1][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }
        //checks to see if third row if either x's or o's
        if (textViews[2][0].getText().equals("X") && textViews[2][1].getText().equals("X") && textViews[2][2].getText().equals("X") ) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[2][0].getText().equals("O") && textViews[2][1].getText().equals("O") && textViews[2][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }
        //checks to see if left to right diagonal is either all x's or o's
        if (textViews[0][0].getText().equals("X") && textViews[1][1].getText().equals("X") && textViews[2][2].getText().equals("X") ) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[0][0].getText().equals("O") && textViews[1][1].getText().equals("O") && textViews[2][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }
        //checks to see if either right to left diagonal is either all x's or o's
        if (textViews[2][0].getText().equals("X") && textViews[1][1].getText().equals("X") && textViews[0][2].getText().equals("X") ) {
            Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        } else if (textViews[2][0].getText().equals("O") && textViews[1][1].getText().equals("O") && textViews[0][2].getText().equals("O")) {
            Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
        }

    }

    public boolean checkIfAlreadyAsked(Question question)
    {
        for(int i =0;i<askedQuestionArrayList.size();i++)
        {
            if(question.getQuestion().equals(askedQuestionArrayList.get(i).getQuestion()))
            {
                return true;
            }
        }
        return false;

    }

    public void printQuestionAskedarraylist()
    {
        for(int i =0;i<askedQuestionArrayList.size();i++)
        {
            Log.d("question " +i, askedQuestionArrayList.get(i).getQuestion());
        }
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
                myIntent.putExtra(MENU_NUM,"4");
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}