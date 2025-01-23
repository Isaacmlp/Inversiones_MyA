package Model.Cliente;

import Utils.ConectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarClienteModel {
    ConectBD Conect = new ConectBD();

    public boolean EliminarCliente(String cedula) {
        String sql = "DELETE FROM Clientes.Cliente WHERE Cedula = ?";

        try (Connection conexion = Conect.Conect();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
