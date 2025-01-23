package Model.Facturar;

import Model.Cliente.Cliente;
import Model.Facturas.FacturasModel;
import Utils.ConectBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DasboardFacturarModel {
    ConectBD Conect = new ConectBD();


    public void cargarProductosFacturaTabla(TableColumn<ProductoFactura,String> NombreProductoColumn, TableColumn<ProductoFactura, Double> CantidadColumn, TableColumn<ProductoFactura, Double> PrecioUSDColumn, TableColumn<ProductoFactura, Double> PrecioBSColumn,  TableView<ProductoFactura> TablaProductos) {
        // Configurar las columnas
        NombreProductoColumn.setCellValueFactory(new PropertyValueFactory<>("NombreFacturado"));
        CantidadColumn.setCellValueFactory(new PropertyValueFactory<>("CantidadFacturado"));
        PrecioUSDColumn.setCellValueFactory(new PropertyValueFactory<>("PrecioUSD"));
        PrecioBSColumn.setCellValueFactory(new PropertyValueFactory<>("PrecioBS"));

        // Cargar los datos del vehículo
    }

    public void CargarProductosFacturados(TableView<ProductoFactura> TablaInventario,String Nombre) {
        ArrayList<String> Producto = BuscarProduto(Nombre);
        ObservableList<ProductoFactura> datos = FXCollections.observableArrayList();

        ProductoFactura productoFactura = new ProductoFactura(
                Producto.get(0),
                Double.parseDouble(Producto.get(1)),
                Double.parseDouble(Producto.get(2)),
                Double.parseDouble(Producto.get(3)),
                Double.parseDouble(Producto.get(3)),
                Double.parseDouble(Producto.get(2))
        );
        datos.add(productoFactura);

        TablaInventario.setItems(datos);
    }



    public ArrayList<String> BuscarProduto (String Nombre) {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto WHERE Nombre = ?")
        ) {
            statement.setString(1, Nombre);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("Nombre"));
                Producto.add(resultSet.getString("Cantidad"));
                Producto.add(resultSet.getString("Precio_Venta_USD"));
                Producto.add(resultSet.getString("Precio_Venta_BS"));

            }
            return Producto;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
