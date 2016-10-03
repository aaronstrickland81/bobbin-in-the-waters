package model;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class UserDatabaseInterface {
    private static UserDatabaseInterface instance = null;
    public ArrayList<String[]> userData;

    private UserDatabaseInterface(String fname) throws IOException {
        populateArray(fname);
    }

    public static UserDatabaseInterface getInstance(String fname) throws IOException{
        if (instance == null) {
            instance = new UserDatabaseInterface(fname);
        }
        return instance;
    }

    private void populateArray(String fname) throws IOException {
        userData = new ArrayList<>();
        BufferedReader dataBR = new BufferedReader(new FileReader(new File(fname)));
        String line;

        while ((line = dataBR.readLine()) != null) {
            String[] entries = line.split(",");

            for (String el : entries) {
                el.trim();
            }
            userData.add(entries);
        }
    }
}
