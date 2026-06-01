package com.meditech.database;

import com.meditech.model.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void guardar(Consulta consulta) {

        String sql= """
                INSERT INTO Consulta(
                    expediente_id,
                    doctor_id,
                    diagnostico,
                    tratamiento,
                    observaciones,
                    fecha
                )
                VALUES(?,?,?,?,?,?)
                """;

        try (
                Connection conn =
                        SQLServerConnection
                                .connect();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ){
          stmt.setInt(1, consulta.getExpedienteId());

            stmt.setInt(2, consulta.getDoctorId()
            );

            stmt.setString(3, consulta.getDiagnostico()
            );

            stmt.setString(4, consulta.getTratamiento()
            );

            stmt.setString(5, consulta.getObservaciones()
            );

            stmt.setString(6, consulta.getFecha()
            );

            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Consulta> listarPorExpediente(int expedienteId){

        List<Consulta> lista = new ArrayList<>();

        String sql="""
                SELECT c.*,
                u.nombre AS doctorNombre
                
                FROM Consulta c
                INNER JOIN Usuario u
                ON c.doctor_id=u.idUsuario
                WHERE expediente_id=?
                
                ORDER BY fecha DESC;
        """;

        try (
                Connection conn =
                        SQLServerConnection
                                .connect();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ){
            stmt.setInt(1, expedienteId);

            ResultSet rs= stmt.executeQuery();

            while (rs.next()){

                Consulta consulta= new Consulta();

                consulta.setIdConsulta(rs.getInt("idCOnsulta"));

                consulta.setDiagnostico(rs.getString("diagnostico"));

                consulta.setTratamiento(rs.getString("tratamiento"));

                consulta.setObservaciones(rs.getString("observaciones"));

                consulta.setFecha(rs.getString("fecha"));

                lista.add(consulta);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
