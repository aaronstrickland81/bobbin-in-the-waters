package services;

import model.Report;
import model.WaterSourceReport;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by kavish on 10/16/16.
 */
public class SourceReportInfo {

    /**
     * Add a new report to the database
     *
     * @param report The report to add
     */
    public static void addReport(WaterSourceReport report) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            String query = " insert into userInfo (date, reportNumber, " +
                    "username, location, type, cond) "
                    + "       values  (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, (Date) report.get_date());
            ps.setInt(2, report.get_reportNumber());
            ps.setString(3, report.get_workername());
            ps.setString(4, report.get_location());
            ps.setString(5, report.get_type().toString());
            ps.setString(6, report.get_condition().toString());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @return
     */
    public static ArrayList<WaterSourceReport> getReports() {
        ArrayList<WaterSourceReport> aList = new ArrayList<>();
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
                        .getInt(2), rs.getString(3), rs.getString(4),
                        WaterType.getType(rs.getString(5)), SourceCondition
                        .getCondition(rs.getString(6)));
                aList.add(w);
            }
            con.close();
            return aList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return aList;
    }

}
