package Controller;

import Model.EliminarProductoModel;
import Model.ModificarProductoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class BuscarProductoController {

    ModificarProductoModel Modify = new ModificarProductoModel();
    EliminarProductoModel Eliminar = new EliminarProductoModel();


    @FXML
    private TextField BuscarTXT;

    @FXML
    private Label CantidadTXT;

    @FXML
    private Label DescripcionTXT;

    @FXML
    private Label NombreProductoTXT;

    @FXML
    private Label PrecioCostUSDTXT;

    @FXML
    private Label PrecioCostoBSTXT;

    @FXML
    private Label PrecioVentaBSTXT;

    @FXML
    private Label PrecioVentaUSDTXT;

    @FXML
    void BtnBuscarProducto(ActionEvent event) {
        if (BuscarTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellena todos los campos");
            alert.showAndWait();
            return;
        }

        ArrayList<String> Producto = Modify.BuscarProducto(BuscarTXT.getText());

        if (Producto.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se encontró el producto");
            alert.showAndWait();
        } else {
            NombreProductoTXT.setText(Producto.get(0));
            DescripcionTXT.setText(Producto.get(1));
            CantidadTXT.setText(Producto.get(2));
            PrecioCostUSDTXT.setText(Producto.get(3));
            PrecioCostoBSTXT.setText(Producto.get(4));
            PrecioVentaBSTXT.setText(Producto.get(6));
            PrecioVentaUSDTXT.setText(Producto.get(5));
        }
    }
}
