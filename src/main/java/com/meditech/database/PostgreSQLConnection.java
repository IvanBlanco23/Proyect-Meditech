package com.meditech.database;

import com.meditech.util.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;


public class PostgreSQLConnection {

    private static final String URL=
            ConfigReader.get("postgres.url");

    private static final String USER=
            ConfigReader.get("postgres.user");

    private static final String PASSWORD=
            ConfigReader.get("postgres.password");

    public static Connection connect() {

        try {

            Connection conn=
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Connected to database PostgreSQL successfully");
            return conn;

        }catch (Exception e){
            System.out.println("Error connecting to the PostgreSQL database");

            e.printStackTrace();

            return null;
        }
    }
}
