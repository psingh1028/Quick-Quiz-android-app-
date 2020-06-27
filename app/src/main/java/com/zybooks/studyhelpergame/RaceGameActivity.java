package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class RaceGameActivity extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private boolean player1Turn, player2Turn;

    private UserAndQuesDatabaseManager myUsersDB;
    private TextView player1ID;
    private TextView player2ID;

    private TextView textViewQuestion;
    private TextView textViewPlayer1Score;
    private TextView textViewPlayer2Score;

    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private String finU1S;
    private String finU2S;

    private ArrayList<Question> correctlyAnsweredQuestions;
    private int questionCounter;
    private int questionCountTotal;
    private Question correctQuestion;

    QuestionBag randomBag;

    Menu mMenu;
    private final String MENU_NUM = "MENUNUM";



    private DatabaseManager getDB;
    private RandomizeQuestions RD;


    private long backPressedTime;
    private int p1_score;
    private int p2_score;
    private boolean answered = false;
    private int randomCount = 0;
    private RadioButton [] radioArray;
    private Question [] randQuestions;

    private List<Question> myQuestions;
    private int x;


    Random rand;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_game);
        x=0;


        correctlyAnsweredQuestions = new ArrayList<>();


        textViewQuestion = findViewById(R.id.text_view_question);
        textViewPlayer1Score = findViewById(R.id.player1_score);
        textViewPlayer2Score = findViewById(R.id.player2_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        player1ID = findViewById(R.id.player1_ID);
        player2ID = findViewById(R.id.player2_ID);


        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);

        player1Turn = true;
        player2Turn =false;

        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        rand = new Random();
        int randNum = rand.nextInt(4);

        randQuestions = new Question [4];
        radioArray = new RadioButton [4];
        radioArray[0] = rb1;
        radioArray[1] = rb2;
        radioArray[2] = rb3;
        radioArray[3] = rb4;


        getDB = new DatabaseManager(this);
        myUsersDB = new UserAndQuesDatabaseManager(getApplicationContext());


        finU1S = MainMenuActivity.finalUser1.getUsername();
        finU2S = MainMenuActivity.finalUser2.getUsername();


        randomBag = getDB.getTest(GameChooserActivity.chosen_test);
        myQuestions = randomBag.getQuestionList();
        RD = new RandomizeQuestions(randomBag);
        textViewQuestionCount.setText("Question: " + 1+ "/" + randomBag.bagSize());


        player1ID.setText(finU1S);
        player2ID.setText(finU2S);
        checkPlayerTurn();
        showNextQuestion();




    }



    private void checkAnswer()
    {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        String currentAnswer = rbSelected.getText().toString();
        int answerNr = rbGroup.indexOfChild(rbSelected)+1;

        if(currentAnswer.equals(correctQuestion.getcAnswer()))
        {
            if(player1Turn) {
                p1_score++;
                textViewPlayer1Score.setText("Score: " + p1_score);
                x = MainMenuActivity.finalUser1.getQuesRight();
                x++;
                MainMenuActivity.finalUser1.setQuesRight(x);
                myUsersDB.updateUser(MainMenuActivity.finalUser1);
                player2Turn = true;
                player1Turn = false;

            }
            else if(player2Turn)
            {
                p2_score++;
                textViewPlayer2Score.setText("Score: " + p2_score);
                x = MainMenuActivity.finalUser2.getQuesRight();
                x++;
                MainMenuActivity.finalUser2.setQuesRight(x);
                myUsersDB.updateUser(MainMenuActivity.finalUser2);
                player1Turn = true;
                player2Turn = false;
            }

//
////            correctQuestion = RD.nextRandomItem();
////            textViewQuestion.setText(correctQuestion.getQuestion());
//
//            int correctAnsPos = rand.nextInt(4);
//            radioArray[correctAnsPos].setText(correctQuestion.getcAnswer());
//            for(int i =0; i <radioArray.length; i++)
//            {
//                if(i!=correctAnsPos) {
//                    radioArray[i].setText(RD.nextRandomItem().getcAnswer());
//                }
//            }

////            rb1.setText()
            questionCounter++;
            textViewQuestionCount.setText("Question: " +(questionCounter)+ "/" + questionCountTotal);
            answered = false;
//            buttonConfirmNext.setText("Confirm");
//            rbGroup.clearCheck();
           showNextQuestion();


        }
        else if(!(currentAnswer.equals(correctQuestion.getcAnswer())&& !player1Turn))
        {
            questionCounter++;
            textViewQuestionCount.setText("Question: " +( questionCounter)+ "/" + questionCountTotal);
            player2Turn = true;
            player1Turn = false;
            answered = false;
            x = MainMenuActivity.finalUser1.getQuesWrong();
            x++;
            MainMenuActivity.finalUser1.setQuesWrong(x);
            myUsersDB.updateUser(MainMenuActivity.finalUser1);
            toastMessage("INCORRECT CHOICE Player 1");
            showNextQuestion();
        }

        else if(!(currentAnswer.equals(correctQuestion.getcAnswer())&& !player2Turn))
        {
            questionCounter++;
            textViewQuestionCount.setText("Question: " +( questionCounter)+ "/" + questionCountTotal);
            player1Turn = true;
            player2Turn = false;
            answered = false;

            x = MainMenuActivity.finalUser2.getQuesWrong();
            x++;
            MainMenuActivity.finalUser2.setQuesWrong(x);
            myUsersDB.updateUser(MainMenuActivity.finalUser2);

            toastMessage("INCORRECT CHOICE Player 2");
            showNextQuestion();
        }

        // showSolution();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void checkPlayerTurn()
    {
        if(player1Turn)
        {
            player1ID.setBackground(getDrawable((R.drawable.border)));
            player2ID.setBackground(null);
        }

        else if(player2Turn)
        {
            player2ID.setBackground(getDrawable((R.drawable.border)));
            player1ID.setBackground(null);
        }


    }
    private void showSolution()
    {
//        rb1.setTextColor(Color.RED);
//        rb2.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);
        //use switch case



//        if(questionCounter<questionCountTotal)
//        {
//            buttonConfirmNext.setText("Next");
//        }
//        else
//        {
//            buttonConfirmNext.setText("Finsih");
//        }


    }

    private void showNextQuestion()
    {
//        RD = new RandomizeQuestions(randomBag);

        questionCountTotal = randomBag.bagSize();
        //  Collections.shuffle(myQuestions);
        //toastMessage("Size of list is " + RD.getSize());






        //where the question displayment starts
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter<questionCountTotal)
        {

            correctQuestion = RD.nextRandomItem();
            textViewQuestion.setText(correctQuestion.getQuestion());

            int correctAnsPos = rand.nextInt(4);
            radioArray[correctAnsPos].setText(correctQuestion.getcAnswer());
            for(int i =0; i <radioArray.length; i++)
            {
                if(i!=correctAnsPos) {
                    radioArray[i].setText(RD.nextRandomItem().getcAnswer());
                }
            }

//            rb1.setText()
//            questionCounter++;
//            textViewQuestionCount.setText("Question: " + questionCounter+ "/" + questionCountTotal);
//            answered = false;
//            buttonConfirmNext.setText("Confirm");
//            rbGroup.clearCheck();

            //

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//            startCountDown();
        }else{
            if(p1_score>p2_score)
            {

                Intent i = new Intent(this, MainActivityAnim.class);
                this.startActivity(i);
                toastMessage("Player 1 WINS!");
            }
            else if(p1_score<p2_score)
            {

                Intent i = new Intent(this, MainActivityAnim.class);
                this.startActivity(i);
                toastMessage("Player 2 WINS!");
            }
            else if(p1_score == p2_score)
            {
                toastMessage("It's A DRAW!");
            }
            finishQuiz();
        }

    }

    private void finishQuiz(){
        finish();
    }
//
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void chooseAnswer(View view)
    {
        if(!answered)
        {
            if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
            {
                checkAnswer();
                checkPlayerTurn();
            }
            else{
                toastMessage("Please select an answer");
            }
        }else
        {
            showNextQuestion();
        }



    }

//    public void getNextQuestion()
//    {
//
//
//        try {
//            correctQuestion = RD.nextRandomItem();
//            toastMessage("Size of list is" + RD.getSize());
//            for(int i =0; i<correctlyAnsweredQuestions.size(); i++)
//            {
//                if(correctQuestion.getQuestion().equals(correctlyAnsweredQuestions.get(i).getQuestion()))
//                {
//                    correctQuestion = RD.nextRandomItem();
//                }
//
//            }
//            textViewQuestion.setText(correctQuestion.getQuestion());
//
//            int correctAnsPos = rand.nextInt(4);
//            radioArray[correctAnsPos].setText(correctQuestion.getcAnswer());
//
//           // int randCounter = 0;
//            for (int i = 0; i < radioArray.length; i++) {
//                if (i != correctAnsPos) {
//                    radioArray[i].setText(RD.getRandomAnswer(correctQuestion));
//                }
//            }
//        }
//
//        catch(Exception IndexOutOfBoundException)
//        {
//            toastMessage("Out of bounds caught");
//        }
//
//
//
//    }
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
                myIntent.putExtra(MENU_NUM,"3");
                this.startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startCountDown()
    {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();;
        if(countDownTimer!= null)
        {
            countDownTimer.cancel();
        }
    }
    private void updateCountDownText()
    {
        int minutes = (int)(timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if(timeLeftInMillis<10000)
        {
            textViewCountDown.setTextColor(Color.RED);
        }
        else{
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }



    public void toastMessage(String message)
    {
        Toast toast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }
}
