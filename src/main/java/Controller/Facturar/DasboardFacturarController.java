package Controller.Facturar;

import Model.Cliente.ModificarClienteModel;
import Model.Facturar.DasboardFacturarModel;
import Model.Facturar.ProductoFactura;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class DasboardFacturarController {
    ModificarClienteModel modificarClienteModel = new ModificarClienteModel();
    ArrayList<String> DatosCliente = new ArrayList<>();
    DasboardFacturarModel DasboardFacturar = new DasboardFacturarModel();

    @FXML
    private Label ApellidoTXT;

    @FXML
    private TableColumn<ProductoFactura, Double> CantidadTXT;

    @FXML
    private TextField CedulaTXT;

    @FXML
    private Label CorreoTXT;

    @FXML
    private Label DireccionTXT;

    @FXML
    private TableColumn<ProductoFactura, String> NombreProductoTXT;

    @FXML
    private Label NombreTXT;

    @FXML
    private TableColumn<ProductoFactura, Double> PrecioBSTXT;

    @FXML
    private TableColumn<ProductoFactura, Double> PrecioUSDTXT;

    @FXML
    private Label TelefonoTXT;

    @FXML
    private Label TotalPagarBSTXT;

    @FXML
    private Label TotalPagarUSDTXT;

    @FXML
    private TableView<ProductoFactura> TablaProductos;

    private void BuscarCliente() {
        if (CedulaTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha introducido el cedula");
            alert.setContentText("El campo Cedula no puede estar vacío");
            alert.showAndWait();
            return;
        }

        DatosCliente = modificarClienteModel.BuscarCliente(CedulaTXT.getText());

        if (DatosCliente.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha encontrado el cliente");
            alert.setContentText("El cliente no existe");
            alert.showAndWait();
            return;
        }
        NombreTXT.setText(DatosCliente.get(0));
        ApellidoTXT.setText(DatosCliente.get(1));
        TelefonoTXT.setText(DatosCliente.get(3));
        DireccionTXT.setText(DatosCliente.get(4));
        CorreoTXT.setText(DatosCliente.get(5));
    }

    private void KeyEventCliente() {
        CedulaTXT.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                BuscarCliente();
            }
        });
    }

    @FXML
    void AgregarProducto() {

    }

    @FXML
    void EliminarProducto() {

    }

    @FXML
    void Pagar() {

    }

    @FXML
    public void initialize() {
        KeyEventCliente();
        DasboardFacturar.cargarProductosFacturaTabla(NombreProductoTXT, CantidadTXT, PrecioUSDTXT, PrecioBSTXT, TablaProductos);
    }
}
