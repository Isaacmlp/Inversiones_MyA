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
    private TextField TxtPrecioCostoUSD;

    @FXML
    private Label lblNombreProducto;

    @FXML
    private TextField txtDescripcionProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioVentaUSD;

    @FXML
    private TextField txtUnidadesProducto;

    private final AddProductModel addProductModel = new AddProductModel();

    @FXML
    void GuardarProducto(ActionEvent event) {
            if(txtNombreProducto.getText().isEmpty() || txtDescripcionProducto.getText().isEmpty() || txtUnidadesProducto.getText().isEmpty() || TxtPrecioCostoUSD.getText().isEmpty() || txtPrecioVentaUSD.getText().isEmpty()) {
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
            String PrecioDeCostoUSD = TxtPrecioCostoUSD.getText();
            String PrecioVentaUSD = txtPrecioVentaUSD.getText();


            String Message = addProductModel.GuardarProducto(NombreProducto,Descripcion,Cantidad,PrecioDeCostoUSD,PrecioVentaUSD);

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
        TxtPrecioCostoUSD.setText("");
        txtPrecioVentaUSD.setText("");
    }

    @FXML
    void CalcularPorcentaje(ActionEvent event) {
        if (event.getSource() == ComboPorcentaje) {
            switch (ComboPorcentaje.getValue()) {
                case "5%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.05)));
                }
                case "10%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.1)));
                }
                case "15%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.15)));
                }
                case "20%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.2)));
                }
                case "25%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.25)));
                }
                case "30%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.3)));
                }
                case "35%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.35)));
                }
                case "40%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.4)));
                }
                case "45%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.45)));
                }
                case "50%" -> {
                    txtPrecioVentaUSD.setText(String.format(Locale.US, "%.2f", (Double.parseDouble(TxtPrecioCostoUSD.getText()) * 1.5)));
                }
            }
        }
    }

    @FXML
    void initialize() {
        ComboPorcentaje.getItems().addAll("5%","10%","15%","20%","25%","30%","35%","40%","45%","50%");
    }

}
