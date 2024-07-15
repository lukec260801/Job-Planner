package com.luke.jobapp;
import java.sql.*;

public class database {
    private static final String URL = "jdbc:sqlite:jobs.db";

    public static void createDatabase() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS jobs (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "jobTitle VARCHAR NOT NULL, " +
                        "company VARCHAR NOT NULL, " +
                        "dateApplied DATE NOT NULL, " +
                        "status TEXT, " +
                        "location VARCHAR NOT NULL)";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
