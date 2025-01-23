package Model.Cliente;

import Utils.ConectBD;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgregarClienteModel {
    ConectBD Conect = new ConectBD();

    public boolean GuardarCliente(String nombre,String apellido, String cedula, String telefono, String direccion,  String correo) {
        String sql = "INSERT INTO Clientes.Cliente VALUES (?,?,?,?,?,?)";
        try (Connection conexion = Conect.Conect();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, cedula);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, correo);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return false;
        }
    }

}
