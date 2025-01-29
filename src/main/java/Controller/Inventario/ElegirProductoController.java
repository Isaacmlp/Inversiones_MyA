package Controller.Inventario;

import Controller.Facturar.DashboardFacturarController;
import Model.Inventario.DolarModel;
import Model.Inventario.ElegirProductoModel;
import Model.Inventario.Producto;
import Model.Inventario.VerInventarioModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import Model.Facturar.DashboardFacturarModel;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ElegirProductoController {

    VerInventarioModel VerInventario = new VerInventarioModel();
    DolarModel Dolar = new DolarModel();
    ElegirProductoModel elegirProductoModel = new ElegirProductoModel();
    DashboardFacturarModel DashboardFacturarmodel;
    DashboardFacturarController DashboardFacturar ;

    public ElegirProductoController() {

    }


    ArrayList<String> Producto = new ArrayList<>();

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




    @FXML
    void BtnElegirProducto() {
        enviarInformacion();
    }

    private void enviarInformacion() {
        ArrayList<String> Productos = elegirProductoModel.GetProductTable(TablaInventario);
        if (Double.parseDouble(Productos.get(4)) == 0.0 ||Double.parseDouble(Productos.get(4)) == 0) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setHeaderText("Producto sin stock");
            alert2.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Seleccionar Cantidad");
        alert.setHeaderText("Cuantos unidades desea Llevar?");

        // Crear un TextField para la entrada
        TextField inputField = new TextField();
        inputField.setPromptText("Enter something...");

        // Añadir el TextField al contenido del Alert
        VBox dialogPaneContent = new VBox();
        dialogPaneContent.getChildren().add(inputField);
        alert.getDialogPane().setContent(dialogPaneContent);

        // Mostrar el Alert y esperar la respuesta
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Obtener el texto ingresado en el TextField
                String input = inputField.getText();
            }
        });




        while (Double.parseDouble(Productos.get(4)) < Double.parseDouble(inputField.getText()) || (Double.parseDouble(inputField.getText()) < 0)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Seleccionar Cantidad");
            alert.setHeaderText("Ingrese una cantidad menor o igual al Stock? Stock: " + Productos.get(4));

            // Crear un TextField para la entrada

            inputField.setPromptText("Enter something...");

            // Añadir el TextField al contenido del Alert
            dialogPaneContent = new VBox();
            dialogPaneContent.getChildren().add(inputField);
            alert.getDialogPane().setContent(dialogPaneContent);

            // Añadir el TextField al contenido del Alert


            // Mostrar el Alert y esperar la respuesta
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Obtener el texto ingresado en el TextField
                    String input = inputField.getText();
                }

                if (response == ButtonType.CANCEL) {
                    return;
                }
            });
        }

        Productos.remove(4);
        Productos.add(inputField.getText());

        DashboardFacturarmodel.GetProducto().addAll(Productos);
        DashboardFacturarmodel.setEstado(true);
        ((Stage) BuscarProductoTXT.getScene().getWindow()).close(); // Cerrar la ventana
    }


    private void KeyPressed() {
        BuscarProductoTXT.addEventFilter(KeyEvent.KEY_RELEASED, event -> VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario,BuscarProductoTXT.getText()));
    }

    @FXML
    public void initialize(DashboardFacturarModel dashboardFacturarmodel) {
        this.DashboardFacturarmodel = dashboardFacturarmodel;
        KeyPressed();
        Dolar.ActualizarDolar();
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario);
    }

}
