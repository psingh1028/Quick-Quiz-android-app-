package com.zybooks.studyhelpergame;
public class TicTacToeBoard {
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;
    public int count;

    public String winner;

    public int[][] boardArray;

    public TicTacToeBoard() {
        boardArray = new int[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                boardArray[row][col]=2;
            }
        }
        count = 0;
    }


    public void setBoard(int row, int col, int val)
    {
        boardArray[row][col]=val;
    }

    public void checkWinner(){

        for(int i=0; i<3; i++){
            if(boardArray[i][0] == boardArray[i][1] && boardArray[i][0] == boardArray[i][2]){
                if (boardArray[i][0]==1){
                    result("X Wins!");
                    break;
                }
                else if (boardArray[i][0]==0) {
                    result("O Wins!");
                    break;
                }
            }
        }

        for(int i=0; i<3; i++){
            if(boardArray[0][i] == boardArray[1][i] && boardArray[0][i] == boardArray[2][i]){
                if (boardArray[0][i]==1){
                    result("X Wins!");
                    break;
                }
                else if (boardArray[0][i]==0) {
                    result("O Wins!");
                    break;
                }
            }
        }

        if(boardArray[0][0] == boardArray[1][1] && boardArray[0][0] == boardArray[2][2]){
            if (boardArray[0][0]==1){
                result("X Wins");
            }
            else if (boardArray[0][0]==0) {
                result("0 Wins!");
            }
        }


        if(boardArray[0][2] == boardArray[1][1] && boardArray[0][2] == boardArray[2][0]){
            if (boardArray[0][2]==1){
                result("X Wins!");
            }
            else if (boardArray[0][2]==0) {
                result("0 Wins!");
            }
        }
        if(count>=9)
        {
            result("Draw!!!");
        }

    }
    public String result(String x)
    {
        winner = x;
        return winner;
    }

    public String getWinner() {
        return winner;
    }

    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                int value = boardArray[row][col];
                boardString.append(value);
            }
        }

        return boardString.toString();
    }

    public void restoreState(String gameState) {
        int index = 0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {

                char x = gameState.charAt(index);
                int newInt = (int)x-48;
                boardArray[row][col] =newInt;
                index++;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}