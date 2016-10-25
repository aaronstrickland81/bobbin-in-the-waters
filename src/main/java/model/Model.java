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
    private static ArrayList<WaterSourceReport> _sourceReports = new ArrayList<>();

    private static ArrayList<WaterQualityReport> _qualityReports = new ArrayList<>();

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


    public static void addSourceReport(WaterSourceReport report) {
        _sourceReports.add(report);
    }

    public static ArrayList<WaterSourceReport> getSourceReports() {
        return _sourceReports;
    }

    public static int sourceNumGenerator() {
        return _sourceReports.size() + 1;
    }


    public static void addQualityReport(WaterQualityReport report) {
        _qualityReports.add(report);
    }

    public static ArrayList<WaterQualityReport> getQualityReports() {
        return _qualityReports;
    }

    public static int qualityNumGenerator() {
        return _qualityReports.size() + 1;
    }

}

