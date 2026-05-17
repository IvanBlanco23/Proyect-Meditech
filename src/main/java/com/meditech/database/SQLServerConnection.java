package com.meditech.database;

import com.meditech.util.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnection {

/*    metodo sin ocupar ConfigReader

    private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Proyect_MediTech;encrypt=true;trusServerCertificate=true";
////                                  "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MediTechDB;encrypt=true;trustServerCertificate=true"
    private static final String USER="usr_aas";//"sa";
    private static final String PASSWORD="root1234";//"TU_PASSWORD";<---Cambía esto por la contra' real de tu base de datos

 */

    private static final String url= ConfigReader.get("sqlserver.url");
    private static final String USER= ConfigReader.get("sqlserver.user");
    private static final String PASSWORD= ConfigReader.get("sqlserver.password");

    public static Connection connect(){

        try {
            Connection conn = DriverManager.getConnection(
                    url,
                    USER,
                    PASSWORD
            );

            System.out.println("Connected to database successfully");
            return conn;

        }catch (Exception e){

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

            return null;
        }

    }
}
