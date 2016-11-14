package services;

import model.WaterQualityReport;
import model.enums.PurityCondition;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavish on 10/27/16.
 */
public class QualityReportInfo {

    /**
     * Adds report to database
     *
     * @param report the report to add
     */
    public static void addQualityReport(WaterQualityReport report) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            String query = " insert into qualityReportInfo (date, " +
                    "reportNumber, username, longitude, latitude, cond, " +
                    "virus, chem) "
                    + "        values  (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new Date(report.get_date().getTime()));
            ps.setInt(2, report.get_reportNumber());
            ps.setString(3, report.get_workername());
            ps.setDouble(4, report.get_longitude());
            ps.setDouble(5, report.get_latitude());
            ps.setString(6, report.get_condition().toString());
            ps.setDouble(7, report.get_virusPPM());
            ps.setDouble(8, report.get_chemPPM());

            ps.execute();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the quality reports
     *
     * @return arraylist of quality reports
     */
    public static List<WaterQualityReport> getQualityReports() {
        List<WaterQualityReport> aList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            PreparedStatement ps = con.prepareStatement("select * from " +
                    "qualityReportInfo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.util.Date utilDate = new java.util.Date(rs.getDate(1).getTime());
                WaterQualityReport w = new WaterQualityReport(utilDate, rs
                        .getInt(2), rs.getString(3), rs.getDouble(4), rs
                        .getDouble(5), PurityCondition.getCondition(rs
                        .getString(6)), rs.getDouble(7), rs.getDouble(8));
                aList.add(w);
            }
            con.close();
            return aList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aList;
    }

    /**
     * Gets quality report based off date
     *
     * @param longitude the longitude to search for
     * @param latitude  the latitude to search for
     * @param date      the date to search for
     * @return arraylist of qualityreports matching query
     */
    public static List<WaterQualityReport> getQualityReports(
            Double longitude, Double latitude, String date) {
        List<WaterQualityReport> aList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            PreparedStatement ps = con.prepareStatement("select from " +
                    "qualityReportInfo where longitude = ? and latitude = ? ");
            ps.setDouble(1, longitude);
            ps.setDouble(2, latitude);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String d = rs.getDate(1).toString();
                d = d.substring(0, 4);
                if (d.equals(date)) {
                    java.util.Date utilDate = new java.util.Date(rs.getDate(1).getTime());
                    WaterQualityReport w = new WaterQualityReport(utilDate, rs
                            .getInt(2), rs.getString(3), rs.getDouble(4), rs
                            .getDouble(5), PurityCondition.getCondition(rs
                            .getString(6)), rs.getDouble(7), rs.getDouble(8));
                    aList.add(w);
                }
            }
            con.close();
            return aList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aList;
    }

    /**
     * Gets the number of rows of reports
     *
     * @return the number of quality reports
     */
    public static int getQualityCounter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) AS rowcount FROM " +
                    "qualityReportInfo");
            rs.next();
            int count = rs.getInt("rowcount");
            con.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}

