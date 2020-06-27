package com.zybooks.studyhelpergame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivityAnim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        TextView text;
        text = findViewById(R.id.text);
        text.setVisibility(View.VISIBLE);


        ImageView translate = (ImageView) findViewById(R.id.one);
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim);
        translate.startAnimation(translateAnimation);

        ImageView translate1 = (ImageView) findViewById(R.id.two);
        Animation translateAnimation1 = AnimationUtils.loadAnimation(this, R.anim.anim2);
        translate1.startAnimation(translateAnimation1);

        ImageView translate2 = (ImageView) findViewById(R.id.three);
        Animation translateAnimation2 = AnimationUtils.loadAnimation(this, R.anim.anim3);
        translate2.startAnimation(translateAnimation2);


        ImageView translate3 = (ImageView) findViewById(R.id.four);
        Animation translateAnimation3 = AnimationUtils.loadAnimation(this, R.anim.anim4);
        translate3.startAnimation(translateAnimation3);

        ImageView translate4 = (ImageView) findViewById(R.id.fifth);
        Animation translateAnimation4 = AnimationUtils.loadAnimation(this, R.anim.anim5);
        translate4.startAnimation(translateAnimation4);

        ImageView translate5 = (ImageView) findViewById(R.id.six);
        Animation translateAnimation5 = AnimationUtils.loadAnimation(this, R.anim.anim6);
        translate5.startAnimation(translateAnimation5);

        ImageView translate6 = (ImageView) findViewById(R.id.seven);
        Animation translateAnimation6 = AnimationUtils.loadAnimation(this, R.anim.anim7);
        translate6.startAnimation(translateAnimation6);

        ImageView translate7 = (ImageView) findViewById(R.id.eight);
        Animation translateAnimation7 = AnimationUtils.loadAnimation(this, R.anim.anim8);
        translate7.startAnimation(translateAnimation7);

        ImageView translate8 = (ImageView) findViewById(R.id.nine);
        Animation translateAnimation8 = AnimationUtils.loadAnimation(this, R.anim.anim9);
        translate8.startAnimation(translateAnimation8);


        ImageView translate9 = (ImageView) findViewById(R.id.ten);
        Animation translateAnimation9 = AnimationUtils.loadAnimation(this, R.anim.anim10);
        translate9.startAnimation(translateAnimation9);


        ImageView translate10 = (ImageView) findViewById(R.id.eleven);
        Animation translateAnimation10 = AnimationUtils.loadAnimation(this, R.anim.anim11);
        translate10.startAnimation(translateAnimation10);


        ImageView translate11 = (ImageView) findViewById(R.id.twelve);
        Animation translateAnimation11 = AnimationUtils.loadAnimation(this, R.anim.anim12);
        translate11.startAnimation(translateAnimation11);


        ImageView translate12 = (ImageView) findViewById(R.id.thirteen);
        Animation translateAnimation12 = AnimationUtils.loadAnimation(this, R.anim.anim13);
        translate12.startAnimation(translateAnimation12);


        ImageView translate13 = (ImageView) findViewById(R.id.fourteen);
        Animation translateAnimation13 = AnimationUtils.loadAnimation(this, R.anim.anim14);
        translate13.startAnimation(translateAnimation13);


        ImageView translate14 = (ImageView) findViewById(R.id.fifteen);
        Animation translateAnimation14 = AnimationUtils.loadAnimation(this, R.anim.anim15);
        translate14.startAnimation(translateAnimation14);


        ImageView translate15 = (ImageView) findViewById(R.id.sixteen);
        Animation translateAnimation15 = AnimationUtils.loadAnimation(this, R.anim.anim16);
        translate15.startAnimation(translateAnimation15);


        ImageView translate16 = (ImageView) findViewById(R.id.seventeen);
        Animation translateAnimation16 = AnimationUtils.loadAnimation(this, R.anim.anim17);
        translate16.startAnimation(translateAnimation16);

    }

}
