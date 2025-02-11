package Model.Inventario;
import Utils.ConectBD;
import Utils.GetCurrency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class ModificarProductoModel {

    ConectBD Conect = new ConectBD();
    GetCurrency Price = new GetCurrency();

    public ArrayList<String> BuscarProducto (String NombreProducto) {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto WHERE Nombre = ?")
        ) {
            statement.setString(1,NombreProducto);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("Nombre"));
                Producto.add(resultSet.getString("Descripcion"));
                Producto.add(resultSet.getString("Cantidad"));
                Producto.add(resultSet.getString("Precio_Costo_USD"));
                Producto.add(resultSet.getString("Precio_Costo_BS"));
                Producto.add(resultSet.getString("Precio_Venta_USD"));
                Producto.add(resultSet.getString("Precio_Venta_BS"));
                Producto.add(resultSet.getString("IDProducto"));
            }
            return Producto;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> BuscarProductoID (int ID) {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto WHERE IDProducto = ?")
        ) {
            statement.setInt(1,ID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("Nombre"));
                Producto.add(resultSet.getString("Descripcion"));
                Producto.add(resultSet.getString("Cantidad"));
                Producto.add(resultSet.getString("Precio_Costo_USD"));
                Producto.add(resultSet.getString("Precio_Costo_BS"));
                Producto.add(resultSet.getString("Precio_Venta_USD"));
                Producto.add(resultSet.getString("Precio_Venta_BS"));
                Producto.add(resultSet.getString("IDProducto"));
            }
            return Producto;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ModificarProducto (String NombreProducto, String Descripcion, String Cantidad, String PrecioCostoUSD, String PrecioCostoBS, String PrecioVentaUSD, String PrecioVentaBS,String ID) {
            PrecioCostoBS = String.valueOf(Double.parseDouble(PrecioCostoUSD) * Price.getCurrency().bcv());
            PrecioVentaBS = String.valueOf(Double.parseDouble(PrecioVentaUSD) * Price.getCurrency().bcv());

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("UPDATE Inventario.Producto SET Nombre = ?, Descripcion = ?, Cantidad = ?, Precio_Costo_USD = ?, Precio_Costo_BS = ?, Precio_Venta_USD = ?, Precio_Venta_BS = ? WHERE IDProducto = ?")
        ) {
            statement.setString(1,NombreProducto);
            statement.setString(2,Descripcion);
            statement.setString(3,Cantidad);
            statement.setString(4,PrecioCostoUSD);
            statement.setString(5,PrecioCostoBS);
            statement.setString(6,PrecioVentaUSD);
            statement.setString(7,PrecioVentaBS);
            statement.setInt(8,Integer.parseInt(ID));
            statement.executeUpdate();
            return true;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
