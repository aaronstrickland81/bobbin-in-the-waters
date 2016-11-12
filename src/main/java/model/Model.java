package model;


import services.QualityReportInfo;
import services.SourceReportInfo;
import services.UserInfoTable;

import javax.xml.transform.Source;
import java.util.ArrayList;

public class Model {
    /**
     * Set Model up as a singleton design pattern
     */
    private static final Model instance = new Model();

    /** the current, logged in user */
    private static User _user;


    /**ArrayList of all reports **/
    private static ArrayList<Report> _sourceReports = new ArrayList<>();

    private static ArrayList<Report> _qualityReports = new ArrayList<>();

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

    public static void addUser(User aUser) {
        UserInfoTable.addUser(aUser);
    }

    public static void editUser(User aUser) {
        UserInfoTable.updateUser(aUser);
    }

    public static User verifyLogin(String uname, String pass) {
        return UserInfoTable.verifyUser(uname, pass);
    }

    public static boolean doesUsernameExist(String uname) {
        return UserInfoTable.checkUserExists(uname);
    }


    public static void addSourceReport(WaterSourceReport report) {
        //_sourceReports.add(report);
        SourceReportInfo.addSourceReport(report);
    }

    public static ArrayList<WaterSourceReport> getSourceReports() {
        //return _sourceReports;
        return SourceReportInfo.getSourceReports();
    }

    public static int sourceNumGenerator() {
        //return _sourceReports.size() + 1;
        return SourceReportInfo.getSourceCounter() + 1;
    }


    public static void addQualityReport(WaterQualityReport report) {
        // _qualityReports.add(report);
        QualityReportInfo.addQualityReport(report);
    }

    public static ArrayList<WaterQualityReport> getQualityReports() {
        //return _qualityReports;
        return QualityReportInfo.getQualityReports();
    }

    public static int qualityNumGenerator() {
        //return _qualityReports.size() + 1;
        return QualityReportInfo.getQualityCounter() + 1;
    }

}

