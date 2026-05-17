package com.meditech.database;

import com.meditech.model.Cita;
import com.meditech.model.EstadoCita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public List<Cita> listarCitas() {

        List<Cita> lista = new ArrayList<>();

        String  sql = "SELECT * FROM citas";

        try (
                Connection conn = SQLServerConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()
        ){

            while (rs.next()) {
                Cita c = new Cita();

                c.setId(rs.getInt("id"));

                c.setPacienteId(rs.getInt("paciente_id"));

                c.setFecha(rs.getString("fecha"));

                c.setMotivo(rs.getString("motivo"));

                c.setEstado(EstadoCita.valueOf(rs.getString("estado")));

                lista.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public int contarCitas() {

        String  sql = "SELECT * FROM citas";

        try (
                Connection conn =
                        SQLServerConnection.connect();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ){

            if (rs.next()){

                return  rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Cita> listarCitasPorDoctor(String doctor){

        List<Cita> lista=new ArrayList<>();

        String sql= """
                SELECT * FROM Cita
                WHERE doctor=? ;
                """;

        try (Connection conn =
                     SQLServerConnection
                             .connect();

             PreparedStatement stmt =
                     conn.prepareStatement(sql)
        ){

            stmt.setString(1, doctor);

            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                Cita c=new Cita();

                c.setId(rs.getInt("id"));

                c.setPacienteId(rs.getInt("paciente_id"));

                c.setFecha(rs.getString("fecha"));

                c.setMotivo(rs.getString("motivo"));

                c.setEstado(EstadoCita.valueOf(rs.getString("estado")));

                lista.add(c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
