package com.meditech.database;

import com.meditech.model.Expediente;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ExpedienteDAO {

    public void guardar(Expediente expediente) {

        String sql= """
                INSERT INTO Expediente(
                paciente_id,
                alergias,
                antecedentes,
                tipo_sangre                
                )
                
                VALUES(?,?,?,?)
                """;

        try (
                Connection conn =
                        SQLServerConnection
                                .connect();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ){
            stmt.setInt(1, expediente.getIdPaciente());

            stmt.setString(2, expediente.getAlergias());

            stmt.setString(3, expediente.getAntecedentes());

            stmt.setString(4, expediente.getTipoSangre());

            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
