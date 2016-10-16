package database;

import model.Report;
import model.User;
import model.enums.AccountType;

import java.sql.*;

import static model.enums.AccountType.getType;


/**
 * Created by kavish on 10/8/16.
 */
public class MySQLdb {


    /**
     * Adds new user to database
     *
     * @param user The user to add
     */
    public static void addUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");

            String query = " insert into userInfo (firstName, lastName, " +
                    "username, email, password, accountType)"
                    + " values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, user.getFname());
            preparedStmt.setString(2, user.getLname());
            preparedStmt.setString(3, user.getUname());
            preparedStmt.setString(4, user.getEmail());
            preparedStmt.setString(5, user.getPassword());
            preparedStmt.setString(6, user.getType().toString());

            preparedStmt.execute();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Update user in database
     *
     * @param user The user to edit
     */
    public static void updateUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");

            PreparedStatement ps = con.prepareStatement("UPDATE userInfo SET firstName = " +
                    "?, lastName = ?, email = ?, password = ? WHERE username = ?");
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUname());
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Check if the username/password combo is valid
     *
     * @param uname the username to check
     * @param pass  the password to check
     * @return the user object
     */
    public static User verifyUser(String uname, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");

            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from userInfo where " +
                    "username = ?");
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountType a = getType(rs.getString(6));
                String userName = rs.getString(3);
                String password = rs.getString(5);
                if (userName.equals(uname) && password.equals(pass)) {
                    User u = new User(rs.getString(3), rs.getString(5), a, rs
                            .getString(4), rs.getString(1), rs
                            .getString(2));
                    return u;
                }
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Add a new report to the database
     *
     * @param report
     */
    public static void addReport(Report report) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");
            String query = " insert into userInfo (date, reportNumber, " +
                    "username, location) "
                    + "    values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, (Date) report.get_date());
            ps.setInt(2, report.get_reportNumber());
            ps.setString(3, report.get_workername());
            ps.setString(4, report.get_location());
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
