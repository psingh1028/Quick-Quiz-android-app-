package com.zybooks.studyhelpergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditQuestionActivity extends AppCompatActivity {

    EditText newAnswer, newQuestion;
    Button confirmButton;
    DatabaseManager getDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        newAnswer = findViewById(R.id.newAnswer);
        newQuestion = findViewById(R.id.newQuestion);
        confirmButton = findViewById(R.id.confirmButton);

        getDB = new DatabaseManager(this);




    }

    public void confirmClick(View view)
    {
       String stringNewQuestion = newQuestion.getText().toString();
       String stringNewAnswer = newAnswer.getText().toString();

       Question questionToChange = new Question(stringNewQuestion, stringNewAnswer);


       //getDB.editQuestion(questionToChange);



    }




}
