package com.zybooks.studyhelpergame;

import java.io.Serializable;
import java.util.Comparator;

public class User implements Serializable
{
    private int ID;
    private String username;
    private int quesRight;
    private int quesWrong;

    public User() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuesRight() {
        return quesRight;
    }

    public void setQuesRight(int quesRight) {
        this.quesRight = quesRight;
    }

    public int getQuesWrong() {
        return quesWrong;
    }

    public void setQuesWrong(int quesWrong) {
        this.quesWrong = quesWrong;
    }

    @Override
    public String toString() {
        return "\nUsername: "+username+"\nQuestions Correct: "+ quesRight+ "\nQuestions Wrong: "+quesWrong+"\n";
    }

}

class lowToHigh implements Comparator<User> {
    public int compare(User u1, User u2) {
        return u1.getQuesRight() - u2.getQuesRight();
    }
}

class highToLow implements Comparator<User> {
    public int compare(User u1, User u2) {
        return u2.getQuesRight() - u1.getQuesRight();
    }
}
