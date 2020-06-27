package com.zybooks.studyhelpergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpTextActivity extends AppCompatActivity {

    private String menuCode;
    private final String MENU_NUM = "MENUNUM";
    TextView outHelp;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_text);
        outHelp = findViewById(R.id.helpOutTxt);

        Intent intent = getIntent();
        menuCode = intent.getStringExtra(MENU_NUM);
        if(menuCode.equals("0"))
        {
           x= getString(R.string.mainHelp);
            outHelp.setText(x);

        }
        else if(menuCode.equals("1"))
        {
            x= getString(R.string.addQuestHelp);
            outHelp.setText(x);
        }

        else if(menuCode.equals("2"))
        {
            x= getString(R.string.gameChooseHelp);
            outHelp.setText(x);
        }
        else if(menuCode.equals("3"))
        {
            x= getString(R.string.raceGameHelp);
            outHelp.setText(x);
        }
        else if(menuCode.equals("4"))
        {
            x= getString(R.string.ticTacHelp);
            outHelp.setText(x);
        }
        else
        {
            outHelp.setText("DIDNT WORK???");
        }







    }
}
