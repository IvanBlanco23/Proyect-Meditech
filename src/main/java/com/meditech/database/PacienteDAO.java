package com.meditech.database;

import com.meditech.model.Paciente;
import com.meditech.util.LoggerUtil;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAO {

    public void guardarPaciente(Paciente paciente){

        String sql= """
                INSER INTO Paciente(nombre, edad, telefono)
                Values(?, ?, ?)
                """;

        try (Connection conn= SQLServerConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());

            stmt.executeUpdate();

            System.out.println("Paciente guardado con éxito!");

        }catch (Exception e){

            LoggerUtil.logError(
                    e.getMessage()
            );

            e.printStackTrace();
        }
    }

    public List<Paciente> listarPaciente(){

        List<Paciente> lista = new ArrayList<>();

        String sql="SELECT * FROM Paciente";

        try (Connection conn= SQLServerConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs=stmt.executeQuery()){

            while (rs.next()){
                Paciente paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setTelefono(rs.getString("telefono"));

                lista.add(paciente);
            }
        }catch (Exception e){
            System.out.printf("Error al listar Paciente");
            e.printStackTrace();
        }
        return lista;
    }


}