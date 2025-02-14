package Controller.Inventario;

import Model.Inventario.ModificarProductoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;

public class ModificarProductoController {
    ModificarProductoModel Modify = new ModificarProductoModel();
    private String ID;

    @FXML
    private TextField CantidadTXT;

    @FXML
    private TextField DescripcionTXT;

    @FXML
    private ComboBox<String> IvaCombo;

    @FXML
    private TextField NombreProductoTXT;

    @FXML
    private TextField PrecioCostoBSTXT;

    @FXML
    private TextField PrecioVentaBSTXT;

    @FXML
    private TextField costoTotalBsTxT;

    @FXML
    private ComboBox<String> ganaciaCombo;

    @FXML
    void IvaComboaAccion(ActionEvent event) {
        if (event.getSource() == IvaCombo) {
            if (IvaCombo.getValue().equals("Si")) {
                String nombreProducto = NombreProductoTXT.getText();
                nombreProducto = nombreProducto.replace(" (E)", "");
                NombreProductoTXT.setText(nombreProducto);
                costoTotalBsTxT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(PrecioCostoBSTXT.getText()) * 1.16 )));
            } else if (IvaCombo.getValue().equals("Excento")) {
                if (NombreProductoTXT.getText().contains(" (E)")) {
                    costoTotalBsTxT.setText(PrecioCostoBSTXT.getText());
                    return;
                }
                NombreProductoTXT.setText(NombreProductoTXT.getText() + " (E)");
                costoTotalBsTxT.setText(PrecioCostoBSTXT.getText());
            }
        }
    }

    @FXML
    void gananciaComboAccion(ActionEvent event) {
        if (event.getSource() == ganaciaCombo) {
            switch (ganaciaCombo.getValue()) {
                case "5%"  -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.05)));
                case "10%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.1)));
                case "15%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.15)));
                case "20%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.2)));
                case "25%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.25)));
                case "30%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.3)));
                case "35%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.35)));
                case "40%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.4)));
                case "45%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.45)));
                case "50%" -> PrecioVentaBSTXT.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(costoTotalBsTxT.getText()) * 1.5)));
            }
        }

    }


    @FXML
    void BtnBuscarProducto() {
    }

    private void BuscarCliente(ArrayList<String> Producto) {
        NombreProductoTXT.setText(Producto.get(0));
        DescripcionTXT.setText(Producto.get(1));
        CantidadTXT.setText(Producto.get(2));
        PrecioCostoBSTXT.setText(Producto.get(4));
        PrecioVentaBSTXT.setText(Producto.get(6));
        ID = Producto.get(7);
    }

    @FXML
    void BtnGuardarCambios() {
        if (IsEmptyFields()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellena todos los campos");
            alert.showAndWait();
            return;
        }

        if (Modify.ModificarProducto(NombreProductoTXT.getText(),DescripcionTXT.getText(),CantidadTXT.getText(),costoTotalBsTxT.getText(),PrecioVentaBSTXT.getText(),ID)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Producto Modificado");
            alert.setHeaderText(null);
            alert.setContentText("Producto Modificado Exitosamente");
            alert.showAndWait();
            CleanFields();
            ((Stage) costoTotalBsTxT.getScene().getWindow()).close();
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
        PrecioCostoBSTXT.setText("");
        PrecioVentaBSTXT.setText("");
    }

    private boolean IsEmptyFields() {
        return (NombreProductoTXT.getText().isEmpty() || DescripcionTXT.getText().isEmpty() || CantidadTXT.getText().isEmpty() || PrecioCostoBSTXT.getText().isEmpty() || PrecioVentaBSTXT.getText().isEmpty());

    }

    @FXML
    public void initialize(ArrayList<String> Producto) {
        BuscarCliente(Producto);
        ganaciaCombo.getItems().addAll("5%","10%","15%","20%","25%","30%","35%","40%","45%","50%");
        IvaCombo.getItems().addAll("Si","Excento");
    }
}
