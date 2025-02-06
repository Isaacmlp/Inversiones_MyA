package Model.Inventario;
import Utils.ConectBD;
import Utils.GetCurrency;
import java.sql.*;

import static java.lang.Double.parseDouble;

public class AddProductModel {
    GetCurrency Price = new GetCurrency();
    ConectBD con = new ConectBD();

    public String GuardarProducto (String NombreProducto,String Descripcion, String Cantidad, String PrecioDeCostoBs, String PrecioVentaBs) {
        double PrecioDeCostoUSD = (parseDouble(PrecioDeCostoBs) / Price.getCurrency().bcv());
        double PrecioVentaUSD = (parseDouble(PrecioVentaBs) / Price.getCurrency().bcv());
        double Unidades = Double.parseDouble(Cantidad);

        String SQL = "INSERT INTO Inventario.Producto VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = con.Conect();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1,NombreProducto);
            statement.setString(2,Descripcion);
            statement.setDouble(3,Unidades);
            statement.setDouble(4,PrecioDeCostoUSD);
            statement.setDouble(5, Double.parseDouble(PrecioDeCostoBs));
            statement.setDouble(6,PrecioVentaUSD);
            statement.setDouble(7, Double.parseDouble(PrecioVentaBs));
            statement.executeUpdate();
            return "Producto guardado";
        }catch (Exception e) {
            return e.getMessage();
        }
    }
}
