package services;

import model.User;
import model.enums.AccountType;

import java.sql.*;

import static model.enums.AccountType.getType;

/**
 * Created by kavish on 10/16/16.
 */
public class UserInfoTable {

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

            PreparedStatement ps = con.prepareStatement("UPDATE userInfo SET firstName = ?," +
                    " lastName = ?, email = ?, password = ?, address = ?, " +
                    "title = ?" +
                    " WHERE " +
                    "username = ?");
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getHomeAddress());
            ps.setString(6, user.getTitle());
            ps.setString(7, user.getUname());

            ps.execute();
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
                AccountType account = getType(rs.getString(6));
                String userName = rs.getString(3);
                String password = rs.getString(5);
                if (userName.equals(uname) && password.equals(pass)) {
                    User u = new User(userName, password,
                            account, rs
                            .getString(4), rs.getString(1), rs
                            .getString(2), rs.getString(7), rs.getString(8));
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
     * Checks to see if the username is already in the table
     *
     * @param username The user attempting to register
     * @return A boolean if user exists or not
     */
    public static boolean checkUserExists(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://bobbindb.chwrjcnilfzs.us-west-2.rds" +
                            ".amazonaws.com:3306/bobbin", "root", "password");

            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from userInfo where " +
                    "username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    /**
     *  Probably could be private
     */

    /**
     * MAKE PRIVATE IF CAN (used for JUnits)
     *
     * Check if the username/password combo is valid
     *
     * @param uname the username to check
     * @return the user object
     */
    public static User getUserFromUserName(String uname) {
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
                AccountType account = getType(rs.getString(6));
                String userName = rs.getString(3);
                String password = rs.getString(5);
                if (userName.equals(uname)) {
                    User u = new User(userName, password,
                            account, rs
                            .getString(4), rs.getString(1), rs
                            .getString(2), rs.getString(7), rs.getString(8));
                    return u;
                }
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
