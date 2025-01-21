package Model;

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

public class VerInventarioModel {
    ConectBD Conect = new ConectBD();


    public void cargarInventarioTabla(TableColumn<Producto, String> NombreProducto,TableColumn<Producto, String> Descripcion,TableColumn<Producto, String> Cantidad,TableColumn<Producto, String> PrecioVentaUSD,TableColumn<Producto, String> PrecioVentaBS, TableView<Producto> TablaInventario) {
        // Configurar las columnas
        NombreProducto.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        PrecioVentaUSD.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaUSD"));
        PrecioVentaBS.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaBS"));

        // Cargar los datos del vehículo
        CargarInventario(TablaInventario);
    }

    public void CargarInventario(TableView<Producto> TablaInventario) {
        ArrayList<String> Product = BuscarProducto();
        ObservableList<Producto> datos = FXCollections.observableArrayList();

        for (int i = 0 ; i < Product.size(); i += 7) {

            Producto producto = new Producto(
                    Product.get(i), // Nombre
                    Product.get(i + 1),     // Descripcion
                    Product.get(i + 2),     // Cantidad
                    Product.get(i + 3),     // Precio de Costo USD
                    Product.get(i + 4),     // Precio de Costo BS
                    Product.get(i + 5),     // Precio de Venta USD
                    Product.get(i + 6)   // Precio de Venta BS
            );
            datos.add(producto);
        }

        TablaInventario.setItems(datos);

    }



    public ArrayList<String> BuscarProducto () {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto")
        ) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("Nombre"));
                Producto.add(resultSet.getString("Descripcion"));
                Producto.add(resultSet.getString("Cantidad"));
                Producto.add(resultSet.getString("Precio_Costo_USD"));
                Producto.add(resultSet.getString("Precio_Costo_BS"));
                Producto.add(resultSet.getString("Precio_Venta_USD"));
                Producto.add(resultSet.getString("Precio_Venta_BS"));
            }
            return Producto;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



