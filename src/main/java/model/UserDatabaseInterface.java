package model;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

/**
 * A class which handles accessing the user database.
 *
 * @author Karthik Praturu
 */
public class UserDatabaseInterface {
    private static UserDatabaseInterface instance = null;
    private ArrayList<User> userData;
    private String filename;

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
        filename = fname;
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
            String[] entries = line.split(";");

            User loadedUser = new User(entries[0].trim(),
                                        entries[1].trim(),
                                        AccountType.getType(entries[2].trim()),
                                        entries[3].trim(),
                                        entries[4].trim(),
                                        entries[5].trim());
            userData.add(loadedUser);
        }
    }

    /**
     * Adds a user to the database
     *
     * @param newUser user to add.
     * @return  true if successfully added, false otherwise
     */
    public boolean addUser(User newUser) {
        userData.add(newUser);
    }

    /**
     * Updates database with changes.
     */
    public void close() {
        // Parse array list and remake csv file here.
    }
}
