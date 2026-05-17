package com.meditech.database;

import com.meditech.util.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConnection {

    private static final String URL=
            ConfigReader.get("mariaDB.url");

    private static final String USER=
            ConfigReader.get("mariaDB.user");

    private static final String PASSWORD=
            ConfigReader.get("mariaDB.password");

    public  static Connection connect(){

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Connected to the MariaDB database successfully");

            return conn;

        }catch (Exception e){
            System.out.println("Error connecting to the MariaDB database");

            e.printStackTrace();

            return null;
        }
    }
}
