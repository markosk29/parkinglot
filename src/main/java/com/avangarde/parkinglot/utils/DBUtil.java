package com.avangarde.parkinglot.utils;

import java.sql.*;

public class DBUtil {
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    //public static final String PASS = "123456789";
    public static final String URL = "jdbc:postgresql://localhost/parking_db";
    private Connection conn;


    public Connection getConn() {
        return conn;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn`t connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try{
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn`t close connection: " + e.getMessage());
        }
    }
}
