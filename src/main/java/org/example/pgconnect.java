package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pgconnect {
    public static void main(String args[]) throws SQLException {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/chil";
        String user = "postgres";
        String password = "hellothere";
        try {
            // Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        // PreparedStatement pst = con.prepareStatement(
        // "SELECT * FROM apollo.nutritional_compliance ORDER BY nutritionalcompianceid
        // ASC LIMIT 10");
        // ResultSet rs = pst.executeQuery();

        // while (rs.next()) {
        // System.out.print(rs.getInt(1));
        // System.out.print(": ");
        // System.out.println(rs.getString(2));
        // }

        String query1 = "select count(*) from apollo.nutritional_compliance";
        Statement ps1 = con.createStatement();
        ResultSet rs = ps1.executeQuery(query1);

        rs.next();
        int count = rs.getInt(1);
        System.out.println("Number of records in the nutritional_compliance table: " + count);

        // query1 = "select * from apollo.nutritional_compliance order by random() limit 10;";
        query1 = "SELECT * FROM apollo.nutritional_compliance TABLESAMPLE SYSTEM (5) REPEATABLE (4);";
        ps1 = con.createStatement();
        rs = ps1.executeQuery(query1);

        while (rs.next()) {
            System.out.print(rs.getInt(1));
            System.out.print(": ");
            for (int i = 2; i < 7; i++) {
                System.out.print(rs.getString(i)+" ");
            }
            System.out.println();
        }
    }
}