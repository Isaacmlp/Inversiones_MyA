package Controller.Inventario;

import Model.Inventario.AddProductModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Locale;


public class AddProductController {
    @FXML
    private Button BtnGuardarProducto;

    @FXML
    private ComboBox<String> ComboPorcentaje;

    @FXML
    private TextField TxtPrecioCostoBs;

    @FXML
    private Label lblNombreProducto;

    @FXML
    private TextField txtDescripcionProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioVentaBs;

    @FXML
    private TextField txtUnidadesProducto;

    private final AddProductModel addProductModel = new AddProductModel();

    @FXML
    void GuardarProducto(ActionEvent event) {
            if(txtNombreProducto.getText().isEmpty() || txtDescripcionProducto.getText().isEmpty() || txtUnidadesProducto.getText().isEmpty() || TxtPrecioCostoBs.getText().isEmpty() || txtPrecioVentaBs.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, rellena todos los campos");
                alert.showAndWait();
                return;
            }

            String NombreProducto = txtNombreProducto.getText();
            String Descripcion = txtDescripcionProducto.getText();
            String Cantidad = txtUnidadesProducto.getText();
            String PrecioDeCostoBs = TxtPrecioCostoBs.getText();
            String PrecioVentaBs = txtPrecioVentaBs.getText();


            String Message = addProductModel.GuardarProducto(NombreProducto,Descripcion,Cantidad,PrecioDeCostoBs,PrecioVentaBs);

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Producto Guardado");
            alert2.setHeaderText(null);
            alert2.setContentText(Message);
            alert2.showAndWait();
            CleanFields();
    }

    private void CleanFields() {
        txtNombreProducto.setText("");
        txtDescripcionProducto.setText("");
        txtUnidadesProducto.setText("");
        TxtPrecioCostoBs.setText("");
        txtPrecioVentaBs.setText("");
    }

    @FXML
    void CalcularPorcentaje(ActionEvent event) {
        if (event.getSource() == ComboPorcentaje) {
            switch (ComboPorcentaje.getValue()) {
                case "5%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.05)));
                }
                case "10%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.1)));
                }
                case "15%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.15)));
                }
                case "20%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.2)));
                }
                case "25%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.25)));
                }
                case "30%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.3)));
                }
                case "35%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.35)));
                }
                case "40%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.4)));
                }
                case "45%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.45)));
                }
                case "50%" -> {
                    txtPrecioVentaBs.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoBs.getText()) * 1.5)));
                }
            }
        }
    }

    @FXML
    void initialize() {
        ComboPorcentaje.getItems().addAll("5%","10%","15%","20%","25%","30%","35%","40%","45%","50%");
    }

}
