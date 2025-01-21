package Controller;

import Model.ModificarProductoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ModificarProductoController {
    ModificarProductoModel Modify = new ModificarProductoModel();


    @FXML
    private TextField BuscarTXT;

    @FXML
    private TextField CantidadTXT;

    @FXML
    private TextField DescripcionTXT;

    @FXML
    private TextField NombreProductoTXT;

    @FXML
    private TextField PrecioCostUSDTXT;

    @FXML
    private TextField PrecioCostoBSTXT;

    @FXML
    private TextField PrecioVentaBSTXT;

    @FXML
    private TextField PrecioVentaUSDTXT;

    @FXML
    void BtnBuscarProducto(ActionEvent event) {
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
            PrecioVentaBSTXT.setText(Producto.get(5));
            PrecioVentaUSDTXT.setText(Producto.get(6));
        }

    }

    @FXML
    void BtnGuardarCambios(ActionEvent event) {
        if (IsEmptyFields()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellena todos los campos");
            alert.showAndWait();
            return;
        }

        if (Modify.ModificarProducto(NombreProductoTXT.getText(),DescripcionTXT.getText(),CantidadTXT.getText(),PrecioCostUSDTXT.getText(),PrecioCostoBSTXT.getText(),PrecioVentaUSDTXT.getText(),PrecioVentaBSTXT.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Producto Modificado");
            alert.setHeaderText(null);
            alert.setContentText("Producto Modificado Exitosamente");
            alert.showAndWait();
            CleanFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al modificar el producto");
            alert.showAndWait();
        }

    }

    private void CleanFields() {
        NombreProductoTXT.setText("");
        DescripcionTXT.setText("");
        CantidadTXT.setText("");
        PrecioCostUSDTXT.setText("");
        PrecioCostoBSTXT.setText("");
        PrecioVentaBSTXT.setText("");
        PrecioVentaUSDTXT.setText("");
    }

    private boolean IsEmptyFields() {
        return (NombreProductoTXT.getText().isEmpty() || DescripcionTXT.getText().isEmpty() || CantidadTXT.getText().isEmpty() || PrecioCostUSDTXT.getText().isEmpty() || PrecioCostoBSTXT.getText().isEmpty() || PrecioVentaBSTXT.getText().isEmpty() || PrecioVentaUSDTXT.getText().isEmpty());

    }
}
