package model;


import java.util.ArrayList;

public class Model {
    /**
     * Set Model up as a singleton design pattern
     */
    private static final Model instance = new Model();

    /** the current, logged in user */
    private static User _user;


    /**ArrayList of all reports **/
    private static ArrayList<Report> _reports = new ArrayList<>();

    public static Model getInstance() {
        return instance;
    }



    /**
     * Sets the logged in user to the input user
     *
     * @param aUser The user that is currently logged in
     */
    public static void setUser(User aUser) {
        if (aUser == null) {
            _user = null;
        } else {
            _user = new User(aUser);
        }
    }

    /**
     * Gets the current, logged in user
     *
     * @return the logged in user
     */
    public static User getUser() {
        return _user;
    }



    public static void addReport(Report report) {
        _reports.add(report);
        if (report instanceof WaterSourceReport) {
            //UserInfoTable.addSourceReport(report);
        }
    }

    public static ArrayList<Report> getReports() {
        return _reports;
    }

    public static int reportNumGenerator() {
        return _reports.size() + 1;
    }

}

