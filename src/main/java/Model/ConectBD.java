package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class ConectBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=InversionesMyA";
    private static final String User = "sa";
    private static final String Password = "154296387";

    public Connection Conect() {
        Connection connection;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, User, Password);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos SQL Server: " + e.getMessage());
        }
        return null;
    }
}
