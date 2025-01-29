package Service;
import Model.Rol;
import Model.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import Utils.ConectBD;

public class AutenticacionService {

    ConectBD Conect = new ConectBD();

    private UserDAO UserDAO = new UserDAO();

    public AutenticacionService() {
        this.UserDAO = new UserDAO();
    }

    public Rol getRol(int usuarioId) {
        Set<Rol> roles = UserDAO.getRoles(usuarioId);
        if (roles.isEmpty()) {
            return (Rol) roles;
        }

        for (Rol rol : roles) {
            if (rol.getID() == usuarioId) {
                return rol;
            }
        }
        return (Rol) roles;
    }

    public boolean tieneRol(int usuarioId, String rolNombre) {
        Set<Rol> roles = UserDAO.getRoles(usuarioId);
        for (Rol rol : roles) {
            if (rol.getNombre().equals(rolNombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean Login(String usuario, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT * FROM Usuarios.Users WHERE USERNAME COLLATE Latin1_General_CS_AS = ? AND PASSWORD = ?";

        try (Connection connection = Conect.Conect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isAuthenticated = resultSet.next(); // Si hay un resultado, el usuario es autenticado
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Es mejor imprimir la traza completa del error para depuración
        }
        return isAuthenticated;
    }
    public int GetID(String usuario) {
        int ID = -1;
        String query = "SELECT ID FROM Usuarios.Users WHERE USERNAME = ?";

        try (Connection connection = Conect.Conect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Verificamos si hay resultados
                if (resultSet.next()) {
                    // Si hay un resultado, obtenemos la contraseña
                    ID = resultSet.getInt("ID");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        // Devolvemos la contraseña hasheada o null si no se encontró
        return ID;
    }
}
