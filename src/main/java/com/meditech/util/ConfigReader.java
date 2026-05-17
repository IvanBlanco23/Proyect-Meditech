package com.meditech.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static{

        try {
            props.load(
                    new FileInputStream(
                            "config.properties"
                    )
            );
        }catch (Exception e){

            System.out.println("Error loading config.properties");
            e.printStackTrace();

        }
    }

    public static String get(String key){
        return props.getProperty(key);
    }
}
