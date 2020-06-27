package com.zybooks.studyhelpergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCardActivity extends AppCompatActivity {

    DatabaseManager getMyDB;
    List<String> testAdapterList;
    Spinner testChooser;
    ArrayAdapter<String> testAdapter;
    ArrayList<Question> qArr;
    QuestionBag qb;
    String testName;
    int indexOF;
    Button b1;
    Button b2;

    TextView qTxt;
    TextView ansTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);
        getMyDB = new DatabaseManager(this);
    indexOF = 0;
        List<String> testNamesForAdapter =  getMyDB.getAllTestNames();

        b1 = findViewById(R.id.showAnswerBtn);
        b2 = findViewById(R.id.nextBtn);
        testChooser = findViewById(R.id.testChooser);

        testAdapterList = new ArrayList<>();

        testAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testAdapterList );

        testChooser.setAdapter(testAdapter);

        qTxt = findViewById(R.id.quesText);
        ansTxt = findViewById(R.id.answerTxt);

        for(int i = 0; i<testNamesForAdapter.size(); i++)
        {
            testAdapter.add(testNamesForAdapter.get(i));
            testAdapter.notifyDataSetChanged();

        }
        testChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                testName = testChooser.getSelectedItem().toString();
                qb =getMyDB.getTest(testName);
                qArr = (ArrayList<Question>) qb.getQuestionList();
                Collections.shuffle(qArr);
                qTxt.setText(qArr.get(indexOF).getQuestion());
                ansTxt.setText("");
                b1.setEnabled(true);
                b2.setEnabled(true);

                try
                {
                }

                  catch(Exception e)
                {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    public void showAnswerONCLICk(View view)
    {
    if(indexOF<qArr.size())
    {
        ansTxt.setText(qArr.get(indexOF).getcAnswer());
    }


    }

    public void nextQuestion()
    {
        if(indexOF<qArr.size())
        {
            indexOF++;
            if(indexOF<qArr.size())
            {
                qTxt.setText(qArr.get(indexOF).getQuestion());
                ansTxt.setText("");
            }
            else {
                qTxt.setText("No More Questions!");
                b1.setEnabled(false);
                b2.setEnabled(false);
                indexOF = 0;
            }
        }
        else {
            qTxt.setText("No More Questions!");
            b1.setEnabled(false);
            b2.setEnabled(false);
            indexOF = 0;
        }

    }

    public void nextOnClick(View view)
    {
        nextQuestion();
    }
}
