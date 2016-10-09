package database;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 * Created by kavish on 10/8/16.
 */
public class MySQLdb {


    public static void addUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bobbin", "root", "password");

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

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from userInfo");
            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getString(2) +
                        "  " + rs.getString(3) + "  " + rs.getString(4) +
                        "  " + rs.getString(5) + " " + rs.getString(6));
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bobbin", "root", "password");

            PreparedStatement ps = con.prepareStatement("UPDATE userInfo SET firstName = " +
                    "?, lastName = ?, email = ?, password = ? WHERE username = ?");
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUname());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
