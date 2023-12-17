package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbUtils {
    public static Connection connection;
    public static Connection connect() {
        String url = System.getenv("dbUrl");
        String pass = System.getenv("dbPass");
        String user = System.getenv("dbUser");
        try {
            if(connection == null){
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connected Successfully!");}
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
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
