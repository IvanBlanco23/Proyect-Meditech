package com.meditech.database;

import com.meditech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String username, String password) {

        String sql = """
                SELECT
                    u.idUsuario,
                    u.nombre,
                    u.username,
                    r.clave AS rol
                
                FROM usuarios u
                
                Inner JOIN Roles r
                ON u.idRol = r.idRol
                
                WHERE
                    u.username = ?
                    AND
                    u.password = ?
                    AND
                    u.activo = 1
                """;

        try (
                Connection conn =
                        SQLServerConnection.connect();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)

        ){
            stmt.setString(1, username);

            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));

                usuario.setNombre(rs.getString("nombre"));

                usuario.setUsername(rs.getString("username"));

                usuario.setRol(rs.getString("rol"));

                return usuario;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
