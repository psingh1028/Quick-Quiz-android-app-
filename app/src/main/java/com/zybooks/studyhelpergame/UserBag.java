package com.zybooks.studyhelpergame;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UserBag
{

    Context mContext;


    ArrayList<User> userArray = new ArrayList();
    public static final String FILENAME = "users.txt";

    public void UserBag(Context context)
    {
        mContext = context;
    }



    public void add(User s) throws IOException
    {
        //Unique names
        if(search(s.getUsername())!=null)
        {
            userArray.add(s);
        }

    }

    public User search(String cn) {

        if (cn != "") {
            for (int i = 0; (i < userArray.size()); i++) {
                if (cn.equals(userArray.get(i).getUsername())) {
                    return userArray.get(i);
                }
            }
        }

        return null;
    }

    public User remove(String cn) {
        int i = 0;
        for (User s : userArray) {
            if (s.getUsername().equals(cn)) {
                break;
            }
            i++;
        }
        if (i < userArray.size()) {
            return userArray.remove(i);
        }
        return null;

    }

    public String display() {
        String test = "";
        for (int i = 0; i < userArray.size(); i++) {
            test += ("\n" + userArray.get(i) + "\n");
        }

        return test;
    }

    public void update(User c1) throws IOException {

        for (int i = 0; (i < userArray.size()); i++) {
            if (c1.getUsername().equals(userArray.get(i).getUsername())) {
                userArray.set(i, c1);
            }
        }

    }

    //We can put the get the highest score checker in here since we have the users
    public String getScoresHighToLow()
    {
        String x = "";
        //I forgot how to sort an arraylist object
        return x;
    }


    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = mContext.openFileInput(FILENAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        userArray = (ArrayList<User>) is.readObject();
        is.close();
        fis.close();

    }

    public void saveToFile() throws IOException {
        FileOutputStream fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(userArray);
        os.close();
        fos.close();
    }


//Need to do save and load method






}
