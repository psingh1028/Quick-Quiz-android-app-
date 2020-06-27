package com.zybooks.studyhelpergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizeQuestions {

//    private Question [] questions;
    private ArrayList<Question> toBeShuffled;
    private ArrayList<Question> correctlyAnsweredQuestions;
    private ArrayList<String> randomAnswers;

    private int i, j;

    public RandomizeQuestions(QuestionBag listOfQuestions)
    {
        toBeShuffled = new ArrayList<>(listOfQuestions.getQuestionList());
        randomAnswers = new ArrayList<>();
        correctlyAnsweredQuestions = new ArrayList<>();

        shuffle(toBeShuffled);


        popRandAnswers(toBeShuffled);
        shuffleString(randomAnswers);

        i = 0;
        j = 0;
    }


    public int getSize()
    {
        return toBeShuffled.size();
    }

    public Question getRandomQuestion()
    {
        Random rand = new Random();

        int randNum = rand.nextInt(toBeShuffled.size());
        return toBeShuffled.get(randNum);
    }


    private void shuffle(ArrayList<Question> unShuffledList) {
        Random r = new Random();

        for (int i = 0; i < unShuffledList.size() / 2; i++) {
            int j1 = r.nextInt(unShuffledList.size());
            int j2 = r.nextInt(unShuffledList.size());
            //swap
            Question temp = unShuffledList.get(j1);
            unShuffledList.set(j1, unShuffledList.get(j2));
            unShuffledList.set(j2, temp);

        }


    }
    private void shuffleString(ArrayList<String> unShuffledList) {
        Random r = new Random();

        for (int i = 0; i < unShuffledList.size() / 2; i++) {
            int j1 = r.nextInt(unShuffledList.size());
            int j2 = r.nextInt(unShuffledList.size());
            //swap
            String temp = unShuffledList.get(j1);
            unShuffledList.set(j1, unShuffledList.get(j2));
            unShuffledList.set(j2, temp);

        }


    }


    private void popRandAnswers(ArrayList<Question> myQuestions)
    {
        for(int i =0; i<myQuestions.size(); i++)
        {
            randomAnswers.add(myQuestions.get(i).getcAnswer());
        }
    }

    public String getRandomAnswer(Question correctQuestion)
    {
        int k = 0;
        String [] randAnswers = new String [3];
        if(j==randomAnswers.size())
        {
            j=0;
        }

        String randomAnswer = randomAnswers.get(j);

        while(correctQuestion.getcAnswer().equals(randomAnswer))
        {
            randomAnswer = randomAnswers.get(k);

            k++;
        }


        j++;
        return randomAnswer;
    }
    public void addAnsweredQuestion(Question answered)
    {
        toBeShuffled.remove(answered);
    }
    public Question nextRandomItem()
    {

        if(i==toBeShuffled.size())
        {
            i = 0 ;
        }
        Question item = toBeShuffled.get(i);

        i = i +1;
        return item ;

    }








}
