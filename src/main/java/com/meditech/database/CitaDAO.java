package com.meditech.database;

import com.meditech.controller.NotificacionController;
import com.meditech.model.Cita;
import com.meditech.model.EstadoCita;
import com.meditech.util.LoggerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private NotificacionController notificacionController;

    public List<Cita> listarCitas() {

        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas";

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setPacienteId(rs.getInt("paciente_id"));
                c.setFecha(rs.getString("fecha"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstado(EstadoCita.valueOf(rs.getString("estado")));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int contarCitas() {

        String sql = "SELECT COUNT(*) FROM citas";

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

    public int contarCitasHoy(String doctor) {

        int total = 0;
        String sql = """
                SELECT COUNT(*) FROM citas
                WHERE doctor = ?
                AND fecha::date = CURRENT_DATE
                AND estado = 'PENDIENTE'
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, doctor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Cita> listarCitasPorDoctor(String doctor) {

        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE doctor = ?";

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, doctor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setPacienteId(rs.getInt("paciente_id"));
                c.setFecha(rs.getString("fecha"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstado(EstadoCita.valueOf(rs.getString("estado")));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void reagendar(int idCita, LocalDate nuevaFecha) {

        String sql = """
                UPDATE citas
                SET fecha = ?
                WHERE id = ?
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, nuevaFecha.toString());
            stmt.setInt(2, idCita);
            stmt.executeUpdate();
            if (notificacionController != null)
                notificacionController.agregar("Consulta reagendada");

        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            e.printStackTrace();
        }
    }

    public void marcarAusente(int idCita) {

        String sql = """
                UPDATE citas
                SET estado = 'AUSENTE'
                WHERE id = ?
                """;

        try (
            Connection conn = PostgreSQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idCita);
            stmt.executeUpdate();
            if (notificacionController != null)
                notificacionController.agregar("Consulta suspendida");

        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            e.printStackTrace();
        }
    }
}