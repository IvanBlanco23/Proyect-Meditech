package com.meditech.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class EstadisticaDAO {

    public Map<String, Integer> obtenerCitasPorEstado() {

        Map<String, Integer> datos = new HashMap<>();

        String sql = """
                SELECT estado, COUNT(*) AS total
                FROM citas
                GROUP BY estado
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                datos.put(
                    rs.getString("estado"),
                    rs.getInt("total")
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return datos;
    }
}