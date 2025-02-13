package Model.Inventario;

import Utils.ConectBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VerInventarioModel {
    ConectBD Conect = new ConectBD();
    String NombreUsuario;

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public void cargarInventarioTabla(TableColumn<Producto, String> NombreProducto, TableColumn<Producto, String> Descripcion, TableColumn<Producto, String> Cantidad, TableColumn<Producto, String> PrecioVentaUSD, TableColumn<Producto, String> PrecioVentaBS, TableView<Producto> TablaInventario, TableColumn<Producto, String> IDColumn) {
        // Configurar las columnas
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        PrecioVentaUSD.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaUSD"));
        PrecioVentaBS.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaBS"));

        // Cargar los datos del vehículo
        CargarInventario(TablaInventario);
    }

    public void cargarInventarioTabla(TableColumn<Producto, String> NombreProducto, TableColumn<Producto, String> Descripcion, TableColumn<Producto, String> Cantidad, TableColumn<Producto, String> PrecioVentaUSD, TableColumn<Producto, String> PrecioVentaBS, TableView<Producto> TablaInventario, String BuscarProductoTXT, TableColumn<Producto, String> IDColumn) {
        // Configurar las columnas
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        PrecioVentaUSD.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaUSD"));
        PrecioVentaBS.setCellValueFactory(new PropertyValueFactory<>("PrecioVentaBS"));

        // Cargar los datos del vehículo
        CargarInventario(TablaInventario, BuscarProductoTXT);
    }

    public void CargarInventario(TableView<Producto> TablaInventario) {
        ArrayList<String> Product = BuscarProducto();
        ObservableList<Producto> datos = FXCollections.observableArrayList();

        for (int i = 0; i < Product.size(); i += 8) {

            Producto producto = new Producto(
                    Product.get(i), // ID
                    Product.get(i + 1), // Nombre
                    Product.get(i + 2),     // Descripcion
                    Product.get(i + 3),     // Cantidad
                    Product.get(i + 4),     // Precio de Costo USD
                    Product.get(i + 5),     // Precio de Costo BS
                    Product.get(i + 6),     // Precio de Venta USD
                    Product.get(i + 7)   // Precio de Venta BS

            );
            datos.add(producto);
        }

        TablaInventario.setItems(datos);
    }

    public void CargarInventario(TableView<Producto> TablaInventario, String BuscarProductoTXT) {
        ArrayList<String> Product = BuscarProducto(BuscarProductoTXT);
        ObservableList<Producto> datos = FXCollections.observableArrayList();

        for (int i = 0; i < Product.size(); i += 8) {

            Producto producto = new Producto(
                    Product.get(i), // ID
                    Product.get(i + 1), // Nombre
                    Product.get(i + 2),     // Descripcion
                    Product.get(i + 3),     // Cantidad
                    Product.get(i + 4),     // Precio de Costo USD
                    Product.get(i + 5),     // Precio de Costo BS
                    Product.get(i + 6),     // Precio de Venta USD
                    Product.get(i + 7)   // Precio de Venta BS
            );
            datos.add(producto);
        }

        TablaInventario.setItems(datos);

    }

    public ArrayList<String> BuscarProducto() {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto")
        ) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("IDProducto"));
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

    public ArrayList<String> BuscarProducto(String BuscarProductoTXT) {
        ArrayList<String> Producto = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto WHERE Nombre LIKE ?")
        ) {
            statement.setString(1, "%" + BuscarProductoTXT + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto.add(resultSet.getString("IDProducto"));
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

    public Inventario GetAllInventario() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        int totalProductos;
        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario.Producto")
        ) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Item> Producto = new ArrayList<>();
            while (resultSet.next()) {
                // Usar BigDecimal para cálculos precisos
                BigDecimal cantidad = BigDecimal.valueOf(resultSet.getDouble("Cantidad"));
                BigDecimal precioCostoBS = BigDecimal.valueOf(resultSet.getDouble("Precio_Venta_BS"));
                BigDecimal valorPorProducto = cantidad.multiply(precioCostoBS).setScale(2, RoundingMode.HALF_UP);

                Producto.add(new Item(
                        resultSet.getInt("IDProducto"),
                        resultSet.getString("Nombre"),
                        resultSet.getDouble("Cantidad"),
                        resultSet.getDouble("Precio_Venta_USD"),
                        resultSet.getDouble("Precio_Venta_BS"),
                        valorPorProducto.doubleValue() // Convertir a double si es necesario
                ));

                // Sumar al valor total
                valorTotal = valorTotal.add(valorPorProducto);
            }
            totalProductos = Producto.size();

            // Asegurar que el valor total tenga 2 decimales
            valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);

            return new Inventario(Producto, valorTotal, totalProductos, LocalDate.now(), NombreUsuario);

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



