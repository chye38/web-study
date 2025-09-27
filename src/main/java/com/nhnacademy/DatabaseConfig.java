package com.nhnacademy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final static String DB_URL = "jdbc:mysql://220.67.216.14:13306/nhn_academy_28";
    private final static String USER = "nhn_academy_28";
    private final static String PASSWORD = ")Cc2Hel@p76OdOU0";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public String getDbUrl(){
        return DB_URL;
    }

    public String getUser(){
        return USER;
    }

    public String getPassword(){
        return PASSWORD;
    }
}
