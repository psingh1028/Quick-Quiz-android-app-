package com.zybooks.studyhelpergame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserAndQuesDatabaseManager extends SQLiteOpenHelper {
    private static UserAndQuesDatabaseManager myTestDB;
    private static String DATABASE_NAME = "myTestDBV4";
    private static final String UDATABASE_NAME = "userDB";
    private static final int VERSION = 1;

    private static final class DatabaseAttributes{

        private static final String TABLE_NAME = "myTest";
        private static final String TEST_NAME = "TestName";
        private static final String QUESTION_ID = "ID";
        private static final String QUESTION_COL = "Question";
        private static final String ANSWER_COL = "Answer";

        private static final String UTABLE = "Users";
        private static final String UID = "ID";
        private static final String USERNAME = "UserName";
        private static final String QUESTIONS_RIGHT = "QRight";
        private static final String QUESTIONS_WRONG = "QWrong";


    }

    public UserAndQuesDatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        db.execSQL("CREATE TABLE "+ UserAndQuesDatabaseManager.DatabaseAttributes.TABLE_NAME + " ("+
//                UserAndQuesDatabaseManager.DatabaseAttributes.QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
//
//                + UserAndQuesDatabaseManager.DatabaseAttributes.TEST_NAME + " text,"
//
//                + UserAndQuesDatabaseManager.DatabaseAttributes.QUESTION_COL + " text, "+ UserAndQuesDatabaseManager.DatabaseAttributes.ANSWER_COL
//                + " text)");

        db.execSQL("create table " + UserAndQuesDatabaseManager.DatabaseAttributes.UTABLE+ "("+ UserAndQuesDatabaseManager.DatabaseAttributes.UID+" integer primary key autoincrement, "+ UserAndQuesDatabaseManager.DatabaseAttributes.USERNAME+
                " text, " + UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_RIGHT+ " integer, "+
                UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_WRONG+ " integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + DATABASE_NAME);
        db.execSQL("drop table if exists " + UDATABASE_NAME);

        onCreate(db);

    }

    //           public boolean editQuestion(Question question, String testName)
//        {
//            SQLiteDatabase db = getWritableDatabase();
//            ContentValues newValues = new ContentValues();
//            //newValues.put(DatabaseAttributes.TEST_NAME, testName );
//            newValues.put(DatabaseAttributes.QUESTION_COL, question.getQuestion());
//            newValues.put(DatabaseAttributes.ANSWER_COL, question.getcAnswer());
//
//            db.update(DatabaseAttributes.TABLE_NAME, newValues, DatabaseAttributes.TEST_NAME
//                            +
//                            " = " + testName ,
//                    null);
//
//
//
//
//            return false;
//    }
    public boolean insert(String testName, String question, String answer)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserAndQuesDatabaseManager.DatabaseAttributes.TEST_NAME, testName);
        contentValues.put(UserAndQuesDatabaseManager.DatabaseAttributes.QUESTION_COL, question);
        contentValues.put(UserAndQuesDatabaseManager.DatabaseAttributes.ANSWER_COL, answer);

        long result = db.insert(UserAndQuesDatabaseManager.DatabaseAttributes.TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }


    public void insertUser(User u) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.USERNAME, u.getUsername());
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_RIGHT,u.getQuesRight());
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_WRONG,u.getQuesWrong());

        db.insert(UserAndQuesDatabaseManager.DatabaseAttributes.UTABLE,null,values);

    }

    public void deleteQuestion(String testName, String testQuestion)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(UserAndQuesDatabaseManager.DatabaseAttributes.TABLE_NAME,
                UserAndQuesDatabaseManager.DatabaseAttributes.TEST_NAME + " = " + "'"+  testName + "'"
                        + " AND " + UserAndQuesDatabaseManager.DatabaseAttributes.QUESTION_COL + " = '" + testQuestion + "'"

                , null);
    }
//    public QuestionBag getTest(String testName)
//    {
//
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT " + DatabaseAttributes.TEST_NAME + ", " +
//                DatabaseAttributes.QUESTION_COL + ", "
//                + DatabaseAttributes.ANSWER_COL + " FROM " + DatabaseAttributes.TABLE_NAME
//                + " WHERE " + DatabaseAttributes.TEST_NAME + " = " + "'"+testName + "'";
//
//
//
//        QuestionBag wantedTest = new QuestionBag(testName);
//        //  return sql;
//        try {
//            Cursor cursor = db.rawQuery(sql, new String[]{});
//            if (cursor.moveToFirst()) {
//                do {
////                    int id = cursor.getInt(0);
//                    String returnTestName = cursor.getString(0);
//                    String returnQuestion = cursor.getString(1);
//                    String returnAnswer = cursor.getString(2);
//
//                    Question wantedQuestion = new Question(returnQuestion, returnAnswer);
//                    wantedTest.add(wantedQuestion);
//
//
//                } while (cursor.moveToNext());
//            }
//
//            cursor.close();
//        }
//        catch (SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
//
//        return wantedTest;
//    }

    public List<String> getAllTestNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT DISTINCT " + UserAndQuesDatabaseManager.DatabaseAttributes.TEST_NAME
                + " FROM "
                + UserAndQuesDatabaseManager.DatabaseAttributes.TABLE_NAME;

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
        String count = "SELECT count(*) FROM " + UserAndQuesDatabaseManager.DatabaseAttributes.TABLE_NAME;
        Cursor mCursor = db.rawQuery(count, null);
        mCursor.moveToFirst();
        int icount = mCursor.getInt(0);
        return icount > 0;

    }


    public ArrayList<User> getAllUsers( ) {
        String sqlQuery = "select * from " + UserAndQuesDatabaseManager.DatabaseAttributes.UTABLE;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<User> userList = new ArrayList<User>( );
        while( cursor.moveToNext( ) ) {
            User u = new User();
            u.setID(cursor.getInt(0));
            u.setUsername(cursor.getString(1));
            u.setQuesRight(cursor.getInt(2));
            u.setQuesWrong(cursor.getInt(3));
            userList.add(u);
        }
        db.close( );
        return userList;
    }

    public void  updateUser(User u)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.USERNAME, u.getUsername());
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_RIGHT,u.getQuesRight());
        values.put(UserAndQuesDatabaseManager.DatabaseAttributes.QUESTIONS_WRONG,u.getQuesWrong());
        db.update(UserAndQuesDatabaseManager.DatabaseAttributes.UTABLE, values, "ID= "+u.getID(),null);
    }

}
