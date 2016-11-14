package model;


import services.QualityReportInfo;
import services.SourceReportInfo;
import services.UserInfoTable;

import java.util.List;

/**
 * Singleton for accessing all services
 */
public class Model {
    /**
     * Set Model up as a singleton design pattern
     */
    private static final Model instance = new Model();

    /** the current, logged in user */
    private static User _user;

    /**
     * Returns the current instance
     */
    public static Model getInstance() {
        return instance;
    }


    /**
     * Sets the logged in user
     *
     * @param aUser The user being set
     */
    public static void setUser(User aUser) {
        _user = aUser;
    }

    /**
     * Gets the current, logged in user
     *
     * @return the logged in user
     */
    public static User getUser() {
        return _user;
    }

    /**
     * Adds user to database
     *
     * @param aUser The user to add
     */
    public static void addUser(User aUser) {
        UserInfoTable.addUser(aUser);
    }

    /**
     * Edits user in database
     *
     * @param aUser The user to edit
     */
    public static void editUser(User aUser) {
        UserInfoTable.updateUser(aUser);
    }

    /**
     * Verifies if the user and password is in the database
     *
     * @param uname The username to check
     * @param pass The password to check
     * @return User object if valid otherwise null
     */
    public static User verifyLogin(String uname, String pass) {
        return UserInfoTable.verifyUser(uname, pass);
    }

    /**
     * Checks if the Username is already in the database
     *
     * @param uname The username to check
     * @return A boolean representing if the username is there
     */
    public static boolean doesUsernameExist(String uname) {
        return UserInfoTable.checkUserExists(uname);
    }

    /**
     * Adds Source Report to the database
     *
     * @param report The report to add
     */
    public static void addSourceReport(WaterSourceReport report) {
        SourceReportInfo.addSourceReport(report);
    }

    /**
     * Gives a list of all Source Reports in the database
     *
     * @return an ArrayList of all Source Reports
     */
    public static List<WaterSourceReport> getSourceReports() {
        return SourceReportInfo.getSourceReports();
    }

    /**
     * Numbers the source report
     *
     * @return integer of the new source report number
     */
    public static int sourceNumGenerator() {
        return SourceReportInfo.getSourceCounter() + 1;
    }

    /**
     * Adds quality report to the database
     *
     * @param report the report to add
     */
    public static void addQualityReport(WaterQualityReport report) {
        QualityReportInfo.addQualityReport(report);
    }

    /**
     * Gives a list of all quality reports in the database
     *
     * @return arraylist of all quality reports
     */
    public static List<WaterQualityReport> getQualityReports() {
        return QualityReportInfo.getQualityReports();
    }

    /**
     * Numbers the quality report
     *
     * @return integer of the new quality report
     */
    public static int qualityNumGenerator() {
        return QualityReportInfo.getQualityCounter() + 1;
    }

}

