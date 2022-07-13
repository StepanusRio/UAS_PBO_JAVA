package com.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; // Menampung Query SQL
import java.sql.ResultSet; // Menampung hasil query / Data dari database

public class KoneksiDBMS {
    private static final String driverdbms = ("com.mysql.jdbc.Driver");
    private static final String database = ("jdbc:mysql://localhost:3306/dbriodefa6337");
    private static final String user = "Admin";
    private static final String password = "5etVi-ZSYBrs5E)2";

    public static Connection conn;
    public static Statement stat;
    public static ResultSet rs;

    public static Connection connection() {
        try {
            Class.forName(driverdbms);
            conn = DriverManager.getConnection(database, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Server MYSQL is OFFLINE (Apache/MYSQL)");

        }
        return conn;
    }
}
