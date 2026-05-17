package com.meditech.util;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class LoggerUtil {

    public static void logError(String mensaje){
        try (
                FileWriter writer =
                        new FileWriter(
                                "logs/error.log",
                                true
                        )
                ){

            writer.write(
                    LocalDateTime .now()
                    + "ERROR: "
                    + mensaje
                    +"\n"
            );

        }catch (Exception e){
            System.out.println("Error escribiendo log");
        }
    }
}
