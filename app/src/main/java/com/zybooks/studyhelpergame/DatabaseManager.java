package com.zybooks.studyhelpergame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private static DatabaseManager myTestDB;
    private static String DATABASE_NAME = "myTestDBV3";

    private static final int VERSION = 1;

    private static final class DatabaseAttributes{

        private static final String TABLE_NAME = "myTest";
        private static final String TEST_NAME = "TestName";
        private static final String QUESTION_ID = "ID";
        private static final String QUESTION_COL = "Question";
        private static final String ANSWER_COL = "Answer";
    }

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("CREATE TABLE "+ DatabaseAttributes.TABLE_NAME + " ("+
               DatabaseAttributes.QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "

        + DatabaseAttributes.TEST_NAME + " text,"

       + DatabaseAttributes.QUESTION_COL + " text, "+ DatabaseAttributes.ANSWER_COL
       + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + DATABASE_NAME);
        onCreate(db);

    }

    public boolean editQuestion(Question question, String testName)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        //newValues.put(DatabaseAttributes.TEST_NAME, testName );
        newValues.put(DatabaseAttributes.QUESTION_COL, question.getQuestion());
        newValues.put(DatabaseAttributes.ANSWER_COL, question.getcAnswer());

        db.update(DatabaseAttributes.TABLE_NAME, newValues, DatabaseAttributes.TEST_NAME
                        +
                        " = " + testName ,
                null);




        return false;
    }
    public boolean insert(String testName, String question, String answer)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseAttributes.TEST_NAME, testName);
        contentValues.put(DatabaseAttributes.QUESTION_COL, question);
        contentValues.put(DatabaseAttributes.ANSWER_COL, answer);

        long result = db.insert(DatabaseAttributes.TABLE_NAME, null, contentValues);
        db.close();
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }




    public void deleteQuestion(String testName, String testQuestion)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DatabaseAttributes.TABLE_NAME,
                DatabaseAttributes.TEST_NAME + " = " + "'"+  testName + "'"
                + " AND " + DatabaseAttributes.QUESTION_COL + " = '" + testQuestion + "'"

                , null);
    }
    public QuestionBag getTest(String testName)
    {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT " + DatabaseAttributes.TEST_NAME + ", " +
                DatabaseAttributes.QUESTION_COL + ", "
                + DatabaseAttributes.ANSWER_COL + " FROM " + DatabaseAttributes.TABLE_NAME
                + " WHERE " + DatabaseAttributes.TEST_NAME + " = " + "'"+testName + "'";



        QuestionBag wantedTest = new QuestionBag(testName);
        //  return sql;
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{});
            if (cursor.moveToFirst()) {
                do {
//                    int id = cursor.getInt(0);
                    String returnTestName = cursor.getString(0);
                    String returnQuestion = cursor.getString(1);
                    String returnAnswer = cursor.getString(2);

                    Question wantedQuestion = new Question(returnQuestion, returnAnswer);
                    wantedTest.add(wantedQuestion);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return wantedTest;
    }

    public Question [] getFourQuestions()
    {

        SQLiteDatabase db = getReadableDatabase();

        return null;
    }
    public List<String> getAllTestNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT DISTINCT " + DatabaseAttributes.TEST_NAME
                + " FROM "
                + DatabaseAttributes.TABLE_NAME;

        List<String> l = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{});
            if (cursor.moveToFirst()) {
                do {
//
                    String testName = cursor.getString(0);

                    l.add(testName);




                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }


        return l;

    }


//    public boolean delete(String testName, String question)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//
//        String sqlQuery = "DELETE FROM " + DatabaseAttributes.TABLE_NAME + " WHERE "
//                + DatabaseAttributes.TEST_NAME + " = " + testName + " & " +
//                DatabaseAttributes.QUESTION_COL + " = "  +question;
//        db.execSQL(sqlQuery);
//        return false;
//    }

    public boolean hasData()
    {
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM " + DatabaseAttributes.TABLE_NAME;
        Cursor mCursor = db.rawQuery(count, null);
        mCursor.moveToFirst();
        int icount = mCursor.getInt(0);
        if(icount>0)
        {
            return true;
        }

        return false;
    }


}
