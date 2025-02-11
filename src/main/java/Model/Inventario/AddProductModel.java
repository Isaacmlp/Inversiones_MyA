package Model.Inventario;
import Utils.ConectBD;
import Utils.GetCurrency;
import java.sql.*;

import static java.lang.Double.parseDouble;

public class AddProductModel {
    GetCurrency Price = new GetCurrency();
    ConectBD con = new ConectBD();

    public String GuardarProducto(String NombreProducto, String Descripcion, String Cantidad, String PrecioDeCostoBs, String PrecioVentaBs) {
        try {
            double precioDeCostoBs = parseDouble(PrecioDeCostoBs);
            double precioVentaBs = parseDouble(PrecioVentaBs);
            double precioDeCostoUSD = (precioDeCostoBs / Price.getCurrency().bcv());
            double precioVentaUSD = (precioVentaBs / Price.getCurrency().bcv());
            double unidades = Double.parseDouble(Cantidad);

            String SQL = "INSERT INTO Inventario.Producto VALUES (?,?,?,?,?,?,?)";
            try (Connection connection = con.Conect();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {

                statement.setString(1, NombreProducto);
                statement.setString(2, Descripcion);
                statement.setDouble(3, unidades);
                statement.setDouble(4, precioDeCostoUSD);
                statement.setDouble(5, precioDeCostoBs);
                statement.setDouble(6, precioVentaUSD);
                statement.setDouble(7, precioVentaBs);
                statement.executeUpdate();
                return "Producto guardado";
            }
        } catch (NumberFormatException e) {
            return "Error en la conversión de números: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
}
