package services;

import model.WaterSourceReport;
import model.enums.SourceCondition;
import model.enums.WaterType;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavish on 10/16/16.
 */
public class SourceReportInfo {

    /**
     * Add a new report to the database
     *
     * @param report The report to add
     */
    public static void addSourceReport(WaterSourceReport report) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            String query = " insert into sourceReportInfo (date, " +
                    "reportNumber, " +
                    "username, longitude, latitude, type, cond) "
                    + "        values  (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new Date(report.get_date().getTime()));
            ps.setInt(2, report.get_reportNumber());
            ps.setString(3, report.get_workername());
            ps.setDouble(4, report.get_longitude());
            ps.setDouble(5, report.get_latitude());
            ps.setString(6, report.get_type().toString());
            ps.setString(7, report.get_condition().toString());

            ps.execute();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the source reports
     *
     * @return arraylist of all the reports
     */
    public static List<WaterSourceReport> getSourceReports() {
        List<WaterSourceReport> aList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            PreparedStatement ps = con.prepareStatement("select * from " +
                    "sourceReportInfo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WaterSourceReport w = new WaterSourceReport(rs.getDate(1), rs
                        .getInt(2), rs.getString(3), rs.getDouble(4), rs
                        .getDouble(5), WaterType.getType(rs.getString(6)),
                        SourceCondition.getCondition(rs.getString(7)));
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
     * Gets the number of source reports in the database
     *
     * @return int of the number of source reports
     */
    public static int getSourceCounter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) AS rowcount FROM " +
                    "sourceReportInfo");
            rs.next();
            int count = rs.getInt("rowcount");
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
