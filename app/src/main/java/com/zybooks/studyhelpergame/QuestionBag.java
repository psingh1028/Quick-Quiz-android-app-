package com.zybooks.studyhelpergame;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBag
{


   private ArrayList<Question> qArray;
   private String bagName;

    public QuestionBag (String testName)
    {
        qArray = new ArrayList<>();

        this.bagName = testName;

    }

    public List<Question> getQuestionList()
    {
        return qArray;
    }

    public void add(Question s) {
        qArray.add(s);
    }

    public String getBagName()
    {
        return this.bagName;
    }


    public int bagSize()
    {
        return qArray.size();

    }

    public Question getQuestion(int pos)
    {
        return qArray.get(pos);
    }



    public Question search(String id) {
        String found = "Object Not Found!";

        for (int i = 0; (i < qArray.size()); i++) {
            if (id.equals(qArray.get(i).getId())) {
                return qArray.get(i);
            }
        }

        return null;
    }

    public Question removeQ(String id) {
        int i = 0;
        for (Question s : qArray) {
            if (s.getId().equals(id)) {
                break;
            }
            i++;
        }
        if (i < qArray.size()) {
            return qArray.remove(i);
        }
        return null;

    }

    public String displayQ() {
        String test = "";
        for (int i = 0; i < qArray.size(); i++) {
            test += (qArray.get(i) + "\n");
        }

        return test;
    }


}
