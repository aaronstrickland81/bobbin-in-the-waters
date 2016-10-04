package model;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class UserDatabaseInterface {
    private static UserDatabaseInterface instance = null;
    private ArrayList<User> userData;

    /**
     * Private constructor of this class that initializes an array
     * representing the database.
     *
     * @param fname The path to file
     * @throws IOException if csv is not at specified fname path
     */
    private UserDatabaseInterface(String fname) throws IOException {
        populateArray(fname);
    }

    /**
     * Returns an instance of this class.
     *
     * @param fname the path to file
     * @return an instance of a UserDatabaseInterface
     * @throws IOException if csv is not at specified fname path
     */
    public static UserDatabaseInterface getInstance(String fname) throws IOException{
        if (instance == null) {
            instance = new UserDatabaseInterface(fname);
        }
        return instance;
    }

    /**
     * Verifies that the user is in database
     *
     * @param uname the username to check
     * @param pword password to check
     * @return true if user is in database, false otherwise.
     */
    public boolean verifyUser(String uname, String pword) {
        for (User currentUser : userData) {
            if (currentUser.getUname().equals(uname)
                    && currentUser.getPassword().equals(pword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Populates an ArrayList with data in csv.
     * Each entry of the ArrayList is a user
     * loaded from csv file.
     *
     * @param fname the path to file
     * @throws IOException if csv is not at specified fname path.
     */
    private void populateArray(String fname) throws IOException {
        userData = new ArrayList<>();
        BufferedReader dataBR = new BufferedReader(new FileReader(new File(fname)));
        String line;

        while ((line = dataBR.readLine()) != null) {
            String[] entries = line.split(",");

            for (String el : entries) {
                el.trim();
            }

            User loadedUser = new User(entries[0], entries[1]);
            userData.add(loadedUser);
        }
    }
}
