package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pgconnect {
    public static void main(String args[]) throws SQLException {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/chil";
        String user = "postgres";
        String password = "hellothere";
        try {
            //  Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        PreparedStatement pst = con.prepareStatement("SELECT * FROM apollo.nutritional_compliance ORDER BY nutritionalcompianceid ASC LIMIT 10");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt(1));
            System.out.print(": ");
            System.out.println(rs.getString(2));
        }
    }
}