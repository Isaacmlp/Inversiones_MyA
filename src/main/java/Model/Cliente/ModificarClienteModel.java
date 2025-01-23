package Model.Cliente;

import Utils.ConectBD;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ModificarClienteModel {
    ConectBD Conect = new ConectBD();

    public boolean ModificarCliente(String nombre, String apellido, String cedula, String telefono, String direccion, String correo) {
        String SQl = "UPDATE Clientes.Cliente SET Nombre = ?, Apellido = ?, Telefono = ?, Direccion = ?, Correo = ? WHERE Cedula = ?";

        try (Connection conexion = Conect.Conect();
             PreparedStatement ps = conexion.prepareStatement(SQl)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, correo);
            ps.setString(6, cedula);
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

    public ArrayList<String> BuscarCliente(String cedula) {
        String SQl = "SELECT NOMBRE, APELLIDO, CEDULA, TELEFONO, DIRECCION, CORREO FROM Clientes.Cliente WHERE Cedula = ?";
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection conexion = Conect.Conect();
             PreparedStatement ps = conexion.prepareStatement(SQl)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getString(1));
                resultado.add(rs.getString(2));
                resultado.add(rs.getString(3));
                resultado.add(rs.getString(4));
                resultado.add(rs.getString(5));
                resultado.add(rs.getString(6));
            }
            return resultado;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return null;
        }
    }
}


