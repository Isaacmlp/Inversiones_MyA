package Model;

import Utils.ConectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Utils.ConectBD;

public class UserDAO  {
    ConectBD Conect = new ConectBD();

    public Set<Rol> getRoles(int ID) {

        Set<Rol> roles = new HashSet<>();
        String sql = "SELECT r.ID, r.Nombre " +
                    "FROM Usuarios.Roles r " +
                    "JOIN Usuarios.Users_Roles ur ON r.ID = ur.RoleID " +
                    "JOIN Usuarios.Users u ON u.ID = ur.ID_Usuario " +
                    "WHERE u.ID = ?";


        try (Connection con = Conect.Conect();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, ID);
            ResultSet Result = statement.executeQuery();
            while (Result.next()) {
                Rol rol = new Rol(0,"");
                rol.setID(Result.getInt("ID"));
                rol.setNombre(Result.getString("Nombre"));
                roles.add(rol);
            }


        } catch (SQLException e ) {
            e.printStackTrace();
        }
         return roles;
    }
}
