package Utils;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectBD {
    static Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String User = dotenv.get("DB_USER");
    private static final String Password = dotenv.get("DB_PASSWORD");

    public Connection Conect() {
        Connection connection;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, User, Password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al conectar a la base de datos SQL Server: " + e.getMessage());
            alert.showAndWait();
        }
        return null;
    }

}
