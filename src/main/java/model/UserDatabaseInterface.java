package model;

import java.io.*;
import java.util.ArrayList;

/**
 * A class which handles accessing the user database.
 *
 * @author Karthik Praturu
 */
public class UserDatabaseInterface {
    private static UserDatabaseInterface instance = null;
    private static ArrayList<User> userData;
    private static String filename;

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
    public static boolean verifyUser(String uname, String pword) {
        for (User _user : userData) {
            if (_user.getUname().equals(uname)
                    && _user.getPassword().equals(pword)) {
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
    private static void populateArray(String fname) throws IOException {
        if (userData == null) {
            userData = new ArrayList<>();
        }

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
     */
    public static void addUser(User newUser) {
        if (userData == null) {
            userData = new ArrayList<>();
        }
        userData.add(newUser);
    }

    public static User editUser(User newUser) {
        for (User u : userData) {
            if (newUser.getUname().equals(u.getUname())) {
                u = newUser;
                return u;
            }
        }
        return null;
    }

    /**
     * Updates database with changes.
     */
    public static void close() throws IOException {
        FileWriter in = new FileWriter(new File(".src/main/resources/users.csv"), false);
        for (User _user : userData) {
            String userInfo = _user.getUname() + ";"
                                + _user.getPassword() + ";"
                                + _user.getType().toString() + ";"
                                + _user.getEmail() + ";"
                                + _user.getFname() + ";"
                                + _user.getLname() + "\n";
            in.append(userInfo);
        }
        in.close();
    }
}
