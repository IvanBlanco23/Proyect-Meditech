package com.meditech.service;

import com.meditech.database.*;
import com.meditech.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public class ReplicationService {

    private static final String SQL= """
            INSERT INTO Paciente(nombre, edad, telefono)
            Values(?, ?, ?, ?)""";

//    Replicacion Paciente
    public void replicarPaciente(Paciente paciente){

        guardarSQLServer(paciente);
        guardarPostgreSQL(paciente);
        guardarMariaDB(paciente);
    }

    public void actualizarPaciente(Paciente  paciente){

        actualizarSQLServer(paciente);
        actualizarPostgreSQL(paciente);
        actualizarMariaDB(paciente);
    }

    public void eliminarPaciente(int id){

        eliminarSQLServer(id);
        eliminarPostGreSQL(id);
        eliminarMariaDB(id);

    }


    //    Replicacion de citas
    public void replicarCita(Cita cita){

        guardarCitaSQLServer(cita);
        guardarCitaPostgreSQL(cita);
        guardarCitaMariaDB(cita);
    };


//    SQLServer
    private void guardarSQLServer (Paciente paciente){

        String sql=
                "{CALL insertar_paciente(?, ?, ?)}";

        try (
                Connection connect =
                        SQLServerConnection.connect();

                CallableStatement stmt =
                        connect.prepareCall(SQL)
        ){

            stmt.setString(1,paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());

            stmt.execute();

            System.out.println("Procedure SQL Server ejecutado\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void actualizarSQLServer(Paciente paciente){

        String sql=
                "{CALL actualizar_paciente(?,?, ?, ?)}";

        try (
                Connection conn =
                        SQLServerConnection.connect();

                CallableStatement stmt =
                        conn.prepareCall(sql)
        ){

            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getNombre());
            stmt.setInt(3, paciente.getEdad());
            stmt.setString(4, paciente.getTelefono());

            stmt.execute();

            System.out.println("Paciente actualizado en SQLServer\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void eliminarSQLServer(int id){

        String sql=
                "{CALL eliminar_paciente(?)}";

        try (
                Connection connect = SQLServerConnection.connect();
                CallableStatement stmt =
                        connect.prepareCall(sql)
        ){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Paciente eliminado en SQLServer\n");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void guardarCitaSQLServer(Cita cita){

        String sql=
                "{CALL insertar_cita(?, ?, ?, ?)}";

        try (
                Connection conn=
                        SQLServerConnection.connect();

                CallableStatement stmt=
                        conn.prepareCall(sql)
        ){

            stmt.setInt(1, cita.getPacienteId());

            stmt.setString(2, cita.getFecha());

            stmt.setString(3, cita.getMotivo());

            stmt.setString(4, cita.getDoctor());

            stmt.setString(5, cita.getEstado().name());

            stmt.execute();

            System.out.println("Cita guardada SQL Server");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    PostgreSQL
    private void guardarPostgreSQL (Paciente paciente){

        String postgre=
                "{CALL insertar_paciente(?, ?, ?)}";

        try (
                Connection connect =
                        PostgreSQLConnection.connect();

                CallableStatement stmt =
                    connect.prepareCall(SQL)
        ){

            stmt.setString(1,paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());

            stmt.executeUpdate();

            System.out.println("Paciente guardado en PostgreSQL\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void actualizarPostgreSQL(Paciente paciente){

        String postgre=
                "{CALL actualizar_paciente(?,?, ?, ?)}";

        try (
                Connection conn =
                        PostgreSQLConnection.connect();

                CallableStatement stmt =
                        conn.prepareCall(postgre)
        ){

            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getNombre());
            stmt.setInt(3, paciente.getEdad());
            stmt.setString(4, paciente.getTelefono());

            stmt.execute();

            System.out.println("Paciente actualizado en SQLServer\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void eliminarPostGreSQL(int id){
        String postgreSQL=
                "{CALL eliminar_paciente(?)}";

        try (
                Connection connect = PostgreSQLConnection.connect();
                CallableStatement stmt =
                        connect.prepareCall(postgreSQL)
        ){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Paciente eliminado en PostgreSQL\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void guardarCitaPostgreSQL(Cita cita){

        String postgre=
                "{CALL insertar_cita(?, ?, ?, ?)}";

        try (
                Connection conn=
                        PostgreSQLConnection.connect();

                CallableStatement stmt=
                        conn.prepareCall(postgre)
        ){

            stmt.setInt(1, cita.getPacienteId());

            stmt.setString(2, cita.getFecha());

            stmt.setString(3, cita.getMotivo());

            stmt.setString(4, cita.getDoctor());

            stmt.setString(5, cita.getEstado().name());

            stmt.execute();

            System.out.println("Cita guardada SQL Server");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    MariaDB
    private void guardarMariaDB (Paciente paciente){

        String maria=
                "{CALL insertar_paciente(?, ?, ?)}";

        try (
                Connection connect =
                        MariaDBConnection.connect();

                CallableStatement stmt =
                        connect.prepareCall(SQL)
        ){

            stmt.setString(1,paciente.getNombre());
            stmt.setInt(2, paciente.getEdad());
            stmt.setString(3, paciente.getTelefono());

            stmt.executeUpdate();

            System.out.println("Paciente guardado en MariaDB\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void actualizarMariaDB(Paciente paciente){

        String maria=
                "{CALL actualizar_paciente(?,?, ?, ?)}";

        try (
                Connection conn =
                        MariaDBConnection.connect();

                CallableStatement stmt =
                        conn.prepareCall(maria)
        ){

            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getNombre());
            stmt.setInt(3, paciente.getEdad());
            stmt.setString(4, paciente.getTelefono());

            stmt.execute();

            System.out.println("Paciente actualizado en SQLServer\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void eliminarMariaDB(int id){
        String mariadb=
                "{CALL eliminar_paciente(?)}";

        try (
                Connection connect = MariaDBConnection.connect();
                CallableStatement stmt =
                        connect.prepareCall(mariadb)
        ){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Paciente eliminado en MariaDB\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void guardarCitaMariaDB(Cita cita){

        String maria=
                "{CALL insertar_cita(?, ?, ?, ?)}";

        try (
                Connection conn=
                        MariaDBConnection.connect();

                CallableStatement stmt=
                        conn.prepareCall(maria)
        ){

            stmt.setInt(1, cita.getPacienteId());

            stmt.setString(2, cita.getFecha());

            stmt.setString(3, cita.getMotivo());

            stmt.setString(4, cita.getDoctor());

            stmt.setString(5, cita.getEstado().name());

            stmt.execute();

            System.out.println("Cita guardada SQL Server");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
