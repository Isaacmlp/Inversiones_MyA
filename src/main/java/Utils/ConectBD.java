package Utils;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectBD {
    static Dotenv dotenv = Dotenv.load();

    /* Configuración de la conexión a la base de datos */
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public Connection Conect() {
        try {
            // No es obligatorio si el driver está en el classpath, pero no hace daño
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            showError("Driver JDBC no encontrado. Asegúrate de tener mssql-jdbc.jar en tu proyecto.");
        } catch (SQLException e) {
            showError("Error al conectar a la base de datos SQL Server: " + e.getMessage());
        }
        return null;
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}