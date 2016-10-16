package services;

import model.User;
import model.enums.AccountType;

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
     * @return the user being verified.
     */
    public static User verifyUser(String uname, String pword) {
        for (User _user : userData) {
            if (_user.getUname().equals(uname)
                    && _user.getPassword().equals(pword)) {
                return _user;
            }
        }
        return null;
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
            String[] entries = line.split(",");
            if (entries.length > 5) {
                User loadedUser = new User(entries[0].trim(),
                        entries[1].trim(),
                        AccountType.getType(entries[2].trim()),
                        entries[3].trim(),
                        entries[4].trim(),
                        entries[5].trim());
                userData.add(loadedUser);
            }
        }
        dataBR.close();
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

    /**
     * Edit an existing user
     *
     * @param newUser the configurations of the new
     *                 user will replace the old ones
     * @return The new user if the user has been edited, null otherwise
     */
    public static User editUser(User newUser) {
        for (int i  = 0; i  < userData.size(); i++) {
            User u = userData.get(i);
            if (newUser.getUname().equals(u.getUname())) {
                u = new User(newUser);
                userData.set(i, u);
                return u;
            }
        }
        return null;
    }

    /**
     * Updates database with changes.
     */
    public static void close() throws IOException {

        for(User u: userData) {
            System.out.println(u.getFname());
        }

        FileWriter in = new FileWriter(new File("./src/main/resources/users.csv"), false);
//        FileOutputStream fw = new FileOutputStream(new File("./src/main/resources/users.csv"));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw));
//        String userInfo = "";
        for (User _user : userData) {
            String userInfo = _user.toString() + "\n";
            in.append(userInfo);
        }
//        bw.write(userInfo);
//        bw.close();
        in.close();
    }
}