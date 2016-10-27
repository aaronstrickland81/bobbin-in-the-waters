package services;

import model.WaterQualityReport;
import model.WaterSourceReport;
import model.enums.PurityCondition;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by kavish on 10/27/16.
 */
public class QualityReportInfo {
    
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
            ps.setDate(1, (Date) report.get_date());
            ps.setInt(2, report.get_reportNumber());
            ps.setString(3, report.get_workername());
            ps.setDouble(4, report.get_longitude());
            ps.setDouble(5, report.get_latitude());
            ps.setString(6, report.get_condition().toString());
            ps.setDouble(7, report.get_virusPPM());
            ps.setDouble(8, report.get_chemPPM());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @return
     */
    public static ArrayList<WaterQualityReport> getQualityReports() {
        ArrayList<WaterQualityReport> aList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            PreparedStatement ps = con.prepareStatement("select * from " +
                    "qualityReportInfo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WaterQualityReport w = new WaterQualityReport(rs.getDate(1), rs
                        .getInt(2), rs.getString(3), rs.getDouble(4), rs
                        .getDouble(5), PurityCondition.getCondition(rs
                        .getString(6)), rs.getDouble(7), rs.getDouble(8));
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

