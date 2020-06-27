package com.zybooks.studyhelpergame;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable
{


    public static final String FILENAME = "qID.txt";

    private Context mContext;

    private String question;
    private String cAnswer;
    private String id;
    private static int idNum = 0;

//If we want multiple choice
//    private String f1Answer;
//    private String f2Answer;
//    private String f3Answer;



//    public Question(Context context) throws IOException {
//        mContext = context;
//        readFromFile();
//        saveToFile();
//
//
//    }

    public Question(String question, String cAnswer) {
        this.question = question;
        this.cAnswer = cAnswer;
        this.id = String.valueOf(idNum++);
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getcAnswer() {
        return cAnswer;
    }

    public void setcAnswer(String cAnswer) {
        this.cAnswer = cAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getIdNum() {
        return idNum;
    }

    public static void setIdNum(int idNum) {
        Question.idNum = idNum;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", cAnswer='" + cAnswer + '\'' ;
    }

    private void writeListToStream(FileOutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(idNum);
        writer.close();
    }

    public void readFromFile() throws IOException {

        BufferedReader reader = null;

        try {
            // Read in list from internal file

            FileInputStream inputStream = mContext.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String temp = reader.readLine();
            idNum = Integer.parseInt(temp);
        }
        catch (FileNotFoundException ex) {
            // Ignore
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void saveToFile() throws IOException {

        // Write list to internal file
        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        writeListToStream(outputStream);
    }

}