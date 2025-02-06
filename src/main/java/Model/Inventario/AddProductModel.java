package Model.Inventario;
import Utils.ConectBD;
import Utils.GetCurrency;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

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

    public void GetProduct (DefaultTableModel Model) {
        try (Connection connection = con.Conect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Producto")
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i<= columnCount; i++) {
                    row[i-1] = resultSet.getObject(i);
                }
                Model.addRow(row);
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void FindProductByName (DefaultTableModel Model, String NombreProducto) {
        String Nombre = "'"+NombreProducto+"%'";
        System.out.println(Nombre);
        try (Connection connection = con.Conect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Producto WHERE Nombre LIKE ?");
        ) {
            preparedStatement.setString(1,NombreProducto + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i<= columnCount; i++) {
                    row[i-1] = resultSet.getObject(i);
                }
                Model.addRow(row);
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
