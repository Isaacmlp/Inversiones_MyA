package Utils;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=InversionesMyA";
    private static final String User = "sa";
    private static final String Password = "154296387";

    public Connection Conect() {
        Connection connection;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, User, Password);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

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
