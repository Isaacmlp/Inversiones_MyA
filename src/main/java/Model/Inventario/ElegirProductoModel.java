package Model.Inventario;

import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ElegirProductoModel {


    public ArrayList<String> GetProductTable (TableView<Producto> TablaInventario) {

        Producto producto = TablaInventario.getSelectionModel().getSelectedItem();

        if (producto != null) {
            // Crear el ArrayList y agregar los valores de la fila seleccionada
            ArrayList<String> Product = new ArrayList<>();
            Product.add(producto.getNombre());
            Product.add(producto.getDescripcion());
            Product.add(producto.getPrecioVentaUSD());
            Product.add(producto.getPrecioVentaBS());
            Product.add(producto.getCantidad());
            return Product;

        }
        return null;
    }
}
