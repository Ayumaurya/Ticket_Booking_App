package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbUtils {
    Connection connection;
    public void connect() {
        String url = System.getenv("dbUrl");
        String pass = System.getenv("dbPass");
        String user = System.getenv("dbUser");
        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connected Successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void disconnect(){
        try {
            connection.close();
            System.out.println("Database Disconnected!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
