package Controller.Inventario;

import Model.Inventario.DolarModel;
import Model.Inventario.Producto;
import Model.Inventario.VerInventarioModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class VerInventarioController {
    VerInventarioModel VerInventario = new VerInventarioModel();
    DolarModel Dolar = new DolarModel();

    @FXML
    private TableColumn<Producto, String> CantidadColumn;

    @FXML
    private TableColumn<Producto, String> DescripcionColumn;

    @FXML
    private TableColumn<Producto, String> NombreProductoColumn;

    @FXML
    private TableColumn<Producto, String> PrecioVentaBsColumn;

    @FXML
    private TableColumn<Producto, String> PrecioVentaUSDColumn;

    @FXML
    private TableView<Producto> TablaInventario;

    @FXML
    private TextField BuscarProductoTXT;


    private void KeyPressed() {
        BuscarProductoTXT.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario,BuscarProductoTXT.getText());
        });
    }

    @FXML
    void initialize() {
        KeyPressed();
        Dolar.ActualizarDolar();
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario);
    }

}



