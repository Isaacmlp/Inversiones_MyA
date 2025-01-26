package Model.Facturar;

import Utils.ConectBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardFacturarModel {
    ConectBD Conect = new ConectBD();
    ArrayList<String> Producto = new ArrayList<>();
    Double TotalPagarUSD = 0.0;
    Double TotalPagarBS = 0.0;
    ObservableList<ProductoFactura> datos = FXCollections.observableArrayList();
    ObservableList<ProductoFactura> Data ;
    boolean Estado = false;

    public  ObservableList<ProductoFactura> getDatos() {
        return datos;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        this.Estado = estado;
    }


    public void setProducto(ArrayList<String> producto) {
       this.Producto = producto;
    }

    public ArrayList<String> GetProducto() {
        return Producto;
    }

    public Double GetTotalPagarUSD() {
        return TotalPagarUSD;
    }

    public Double GetTotalPagarBS() {
        return TotalPagarBS;
    }

    public void cargarProductosFacturaTabla(TableColumn<ProductoFactura,String> NombreProductoColumn, TableColumn<ProductoFactura, Double> CantidadColumn, TableColumn<ProductoFactura, Double> PrecioUSDColumn, TableColumn<ProductoFactura, Double> PrecioBSColumn,  TableView<ProductoFactura> TablaProductos,ArrayList<String> Producto) {
        // Configurar las columnas
        NombreProductoColumn.setCellValueFactory(new PropertyValueFactory<>("NombreFacturado"));
        CantidadColumn.setCellValueFactory(new PropertyValueFactory<>("CantidadFacturado"));
        PrecioUSDColumn.setCellValueFactory(new PropertyValueFactory<>("PrecioUSD"));
        PrecioBSColumn.setCellValueFactory(new PropertyValueFactory<>("PrecioBS"));

        // Cargar los datos del vehículo


        CargarProductosFacturados(TablaProductos,Producto);

    }

    public void CargarProductosFacturados(TableView<ProductoFactura> TablaInventario,ArrayList<String> Producto) {
          Data  = FXCollections.observableArrayList();
          for (String producto : Producto) {
              System.out.println(producto + " Index: " + Producto.indexOf(producto));
          }
        for (int i = 0; i < Producto.size(); i += 5) {
            ProductoFactura productoFactura = new ProductoFactura(
                    Producto.get(i),
                    Double.parseDouble(Producto.get(i + 4)),
                    Double.parseDouble(Producto.get(i + 2)),
                    Double.parseDouble(Producto.get(i + 3))
            );
            Data.add(productoFactura);
        }
        TablaInventario.setItems(Data);
    }

    public ArrayList<Double> ObtenerTotales() {
        TotalPagarUSD = 0.0;
        TotalPagarBS = 0.0;

        ArrayList<Double> Totales = new ArrayList<>();
        for (int i = 0; i < Producto.size(); i += 5) {
            TotalPagarUSD += (Double.parseDouble(Producto.get(i + 2)) * Double.parseDouble(Producto.get(i + 4)));
            BigDecimal totalusd = new BigDecimal(TotalPagarUSD);
            totalusd = totalusd.setScale(2, RoundingMode.HALF_UP);
            TotalPagarUSD = totalusd.doubleValue();
        }
        for (int i = 0; i < Producto.size(); i += 5) {
            TotalPagarBS += (Double.parseDouble(Producto.get(i + 3)) * Double.parseDouble(Producto.get(i + 4)));
            BigDecimal totalbs = new BigDecimal(TotalPagarBS);
            totalbs = totalbs.setScale(2, RoundingMode.HALF_UP);
            TotalPagarBS = totalbs.doubleValue();
        }

        Totales.add(TotalPagarUSD);
        Totales.add(TotalPagarBS);


        return Totales;
    }

    /*public void EliminarProductoSeleccionado(TableView<ProductoFactura> TablaInventario) {
        ProductoFactura productoSeleccionado = TablaInventario.getSelectionModel().getSelectedItem();

        // Verificar si hay un producto seleccionado
        if (productoSeleccionado != null) {
            // Obtener la lista de productos (suponiendo que tienes un ObservableList)
             Data = TablaInventario.getItems();

             System.out.println("Producto Seleccionado: " + productoSeleccionado.getNombreFacturado());
             System.out.println("Product.size" + Producto.size());

            System.out.println("------------------------");

            for (String producto : Producto) {
                System.out.println(producto);
            }

            System.out.println("------------------------");

            int o = 5;
            for (int i = 0; i < Producto.size(); i ++) {

                for (int j = 0; j < o-1; j++) {
                    System.out.println("Dentro del for");
                    if (Producto.get(j).equals(productoSeleccionado.getNombreFacturado())) {
                        Producto.remove(j);
                        Producto.remove(j);
                        Producto.remove(j);
                        Producto.remove(j);
                        Producto.remove(j);
                    }
                }
                if (Data.get(i).equals(productoSeleccionado)) {
                    Data.remove(i);
                    break;
                } else {
                    if (i == 0) {
                        o = o * 2;
                    } else {
                        o = o * i;
                    }
                }
            }
            // Eliminar el producto de la lista


            // Limpiar la selección
            TablaInventario.getSelectionModel().clearSelection();
            Estado = true;
        } else {
            // Opcional: mostrar un mensaje si no hay ningún producto seleccionado
            System.out.println("No se ha seleccionado ningún producto para eliminar."); // Mostrar un mensaje
        }

    }*/

    public void EliminarProductoSeleccionado(TableView<ProductoFactura> TablaInventario) {
        // Obtener el producto seleccionado
        ProductoFactura productoSeleccionado = TablaInventario.getSelectionModel().getSelectedItem();

        // Verificar si hay un producto seleccionado
        if (productoSeleccionado != null) {
            // Obtener la lista de productos (suponiendo que tienes un ObservableList)
            ObservableList<ProductoFactura> data = TablaInventario.getItems();

            // Imprimir información del producto seleccionado
            System.out.println("Producto Seleccionado: " + productoSeleccionado);
            System.out.println("Tamaño de la lista antes de eliminar: " + data.size());

            // Eliminar el producto del ArrayList (usando algún método para obtener su nombre o id)
            String nombreProducto = productoSeleccionado.getNombreFacturado(); // Suponiendo que ProductoFacturado tiene un método getNombre()
            for (int i = 0; i < Producto.size(); i++) {
                if (Producto.get(i).equals(nombreProducto)) {
                    Producto.remove(i);
                    Producto.remove(i);
                    Producto.remove(i);
                    Producto.remove(i);
                    Producto.remove(i);
                    Data.remove(productoSeleccionado);
                    data.remove(productoSeleccionado);
                    break;
                }
            }

            // Eliminar el producto del ObservableList


            // Limpiar la selección
            TablaInventario.getSelectionModel().clearSelection();
            Estado = true;

            // Imprimir tamaño después de la eliminación
            System.out.println("Tamaño de la lista después de eliminar: " + data.size());
        } else {
            // Mostrar un mensaje si no hay ningún producto seleccionado
            System.out.println("No se ha seleccionado ningún producto para eliminar.");
        }


    }

}
