package Model;
import Utils.ConectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarProductoModel {
    ConectBD Conect = new ConectBD();

    public boolean EliminarProducto (String NombreProducto) {
        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Inventario.Producto WHERE Nombre = ?")
        ) {
            statement.setString(1,NombreProducto);
            statement.executeUpdate();
            return true;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
