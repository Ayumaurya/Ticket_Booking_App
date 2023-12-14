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
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void disconnect(){
        try {
            connection.close();
            System.out.println("Disconnected!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
