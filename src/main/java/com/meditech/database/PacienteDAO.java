package com.meditech.database;

import com.meditech.model.Paciente;
import com.meditech.util.LoggerUtil;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAO {

    public void guardarPaciente(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes (nombre, edad, telefono)
                VALUES (?, ?, ?)
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());
            stmt.executeUpdate();
            System.out.println("Paciente guardado con éxito!");

        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Paciente> listarPacientes() {

        List<Paciente> lista = new ArrayList<>();

        String sql = "SELECT * FROM pacientes";

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setTelefono(rs.getString("telefono"));
                lista.add(paciente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar Paciente");
            e.printStackTrace();
        }

        return lista;
    }

    public int contarPacientes() {

        String sql = "SELECT COUNT(*) FROM pacientes";

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void actualizarPaciente(Paciente paciente) {

        String sql = """
                UPDATE pacientes
                SET nombre = ?, edad = ?, telefono = ?
                WHERE id = ?
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());
            stmt.setInt(4, paciente.getId());
            stmt.executeUpdate();
            System.out.println("Paciente actualizado con éxito!");

        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {

        String sql = """
                DELETE FROM pacientes
                WHERE id = ?
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Paciente eliminado con éxito!");

        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            e.printStackTrace();
        }
    }
}