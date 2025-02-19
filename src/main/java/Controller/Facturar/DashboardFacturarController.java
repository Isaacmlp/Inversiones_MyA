package Controller.Facturar;

import Model.Cliente.ModificarClienteModel;
import Model.Facturar.DashboardFacturarModel;
import Model.Facturar.ProductoFactura;
import Model.User;
import Utils.OpenView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class DashboardFacturarController {
     ModificarClienteModel modificarClienteModel = new ModificarClienteModel();
     ArrayList<String> DatosCliente = new ArrayList<>();
     DashboardFacturarModel DasboardFacturar ;
    private final OpenView Open = new OpenView();
    private ObservableList<ProductoFactura> Data;
    private ArrayList<Double> Totales;




    private ArrayList<String> Producto = new ArrayList<>();

    public DashboardFacturarController() {
        this.DasboardFacturar =  new DashboardFacturarModel();

    }


    public void handleEnviarProducto(ArrayList<String> productosSeleccionados) {
        Producto.addAll(productosSeleccionados);
        DasboardFacturar.CargarProductosFacturados(TablaProductos, productosSeleccionados);
    }

    public ArrayList<String> getProducto() {
        return Producto;
    }

    public void setProducto(ArrayList<String> producto) {
        Producto = producto;
    }


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
    private TableView<ProductoFactura> TablaProductos = new TableView<>();

    public TableView<ProductoFactura> getTablaProductos() {
        return TablaProductos;
    }



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
        DasboardFacturar.setIDCliente(Integer.parseInt(DatosCliente.get(6)));
    }

    private void KeyEventCliente() {
        CedulaTXT.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                BuscarCliente();
            }
        });
    }

    @FXML
    void AgregarProducto() throws Exception {
        Open.ElegirProducto(DasboardFacturar);

    }

    @FXML
    void EliminarProducto() {
        DasboardFacturar.EliminarProductoSeleccionado(TablaProductos);
    }

    @FXML
    void Pagar() {
        if (CedulaTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos vacios");
            alert.setContentText("Ingrese la Cedula del Cliente");
            alert.showAndWait();
            return;
        }

        if (TablaProductos.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hay productos");
            alert.setContentText("No hay productos para pagar");
            alert.showAndWait();
            return;
        }

        Open.Pagar(DasboardFacturar);


    }

    public void ActualizarVista() {
        if (!DasboardFacturar.GetProducto().isEmpty()) {
            DasboardFacturar.cargarProductosFacturaTabla(NombreProductoTXT, CantidadTXT, PrecioUSDTXT, PrecioBSTXT, TablaProductos, DasboardFacturar.GetProducto());
        }
        Totales = DasboardFacturar.ObtenerTotales();
        TotalPagarUSDTXT.setText(Totales.getFirst().toString() + " $");
        TotalPagarBSTXT.setText(Totales.get(1).toString() + " Bs");
    }

    public void TimelineTest() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (DasboardFacturar.getEstado()) {
                ActualizarVista();
                DasboardFacturar.setEstado(false);
            }

            if (DasboardFacturar.getCleanALL()) {
                Clean();
                DasboardFacturar.setCleanALL(false);
                ((Stage) CorreoTXT.getScene().getWindow()).close();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void Clean() {
        CleanFields();
        CleanProduct();
    }

    private void CleanFields() {
        CedulaTXT.setText("");
        CorreoTXT.setText("");
        ApellidoTXT.setText("");
        DireccionTXT.setText("");
        NombreTXT.setText("");
        TelefonoTXT.setText("");
        TotalPagarBSTXT.setText("");
        TotalPagarUSDTXT.setText("");
    }

    private void CleanProduct() {
       if ( DasboardFacturar.getProducto().isEmpty() || TablaProductos.getItems().isEmpty() || DasboardFacturar.getData().isEmpty()) {
           DasboardFacturar.getProducto().clear();
           DasboardFacturar.EliminartodoslosProductosTabla(TablaProductos);
           DasboardFacturar.getData().clear();
       }
    }

    @FXML
    public void initialize(DashboardFacturarModel dasboardFacturarModel, User user) {
        this.DasboardFacturar = dasboardFacturarModel;
        KeyEventCliente();
        TimelineTest();
    }
}
