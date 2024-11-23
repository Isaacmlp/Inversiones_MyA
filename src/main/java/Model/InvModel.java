package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class InvModel {
    GetCurrency Price = new GetCurrency();
    ConectBD con = new ConectBD();

    public String GuardarProducto (String NombreProducto,String Descripcion, String Cantidad, String PrecioDeCostoUSD, String PrecioVentaUSD) {

        double PrecioCostoBs = (parseDouble(PrecioDeCostoUSD) * Price.getCurrency().bcv());
        double PrecioVentaBs = (parseDouble(PrecioVentaUSD) * Price.getCurrency().bcv());
        int Unidades = parseInt(Cantidad);
        double PrecioCostoUSD = parseDouble(PrecioDeCostoUSD);
        double PrecioVentaUSd = parseDouble(PrecioVentaUSD);

        String SQL = "INSERT INTO Producto(Nombre, Descripcion, Cantidad, Precio_Costo_USD,Precio_Costo_BS ,Precio_Venta_USD, Precio_Venta_BS) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = con.Conect();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1,NombreProducto);
            statement.setString(2,Descripcion);
            statement.setInt(3,Unidades);
            statement.setDouble(4,PrecioCostoUSD);
            statement.setDouble(5,PrecioCostoBs);
            statement.setDouble(6,PrecioVentaUSd);
            statement.setDouble(7,PrecioVentaBs);
            statement.executeUpdate();
            return "Producto guardado";
        }catch (Exception e) {
            return e.getMessage();
        }

    }
}
