package com.zybooks.studyhelpergame;
public class Stats
{
    private int correctAnswers;
    private int wrongAnswers;

    public Stats(int correctAnswers, int wrongAnswers) {
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public String toString() {
        return "Stats\n" +
                "Correct: " + correctAnswers +
                "\nWrong: " + wrongAnswers +
                "\n";
    }
}
