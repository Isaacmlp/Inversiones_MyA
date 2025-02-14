package Controller.Inventario;

import Model.Inventario.*;
import Model.PDF;
import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import Model.FileChoose;
import javafx.scene.layout.VBox;
import org.xhtmlrenderer.css.style.derived.StringValue;

import java.util.Optional;

import static Model.GenerateHTML.generarReporte;

public class VerInventarioController {
    VerInventarioModel VerInventario = new VerInventarioModel();
    DolarModel Dolar = new DolarModel();
    FileChoose FileChoose = new FileChoose();
    User user;
    String NombreUsuario;
    OpenView Open = new OpenView();
    EliminarProductoModel Eliminar = new EliminarProductoModel();
    ModificarProductoModel Modificar = new ModificarProductoModel();

    private String input;

    private boolean isAdmin() {
        return user.getRol().equals("Administrador") || user.getRol().equals("Supervisor");
    }

    @FXML
    private Button BtnEliminarProducto;

    @FXML
    private Button BtnAgregarProducto;


    @FXML
    private Button BtnModificarProducto;


    @FXML
    private Button BtnDescargar;

    @FXML
    private TableColumn<Producto, String> IDColumn;


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
    void DescargarInventario() {
        NombreUsuario = String.format("%s-[%s]", user.getUsername(), user.getRol());
        VerInventario.setNombreUsuario(NombreUsuario);

        Inventario reporte = VerInventario.GetAllInventario();
        if (reporte == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo generar el reporte");
            alert.showAndWait();
            return;
        }


        String HTMl = generarReporte(reporte);
        PDF.generarPDF(HTMl, FileChoose.EscojerRuta(BtnDescargar, String.valueOf(VerInventario.GetAllInventario().getFecha())));
    }

    @FXML
    void AgregarProducto() throws Exception {
        Open.AddProduct();
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario, IDColumn);
    }

    @FXML
    void EliminarProducto() {
        if (isAdmin()) {
            Producto producto = TablaInventario.getSelectionModel().getSelectedItem();

            if (producto == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione un producto para eliminar");
                alert.showAndWait();
                return;
            }

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Eliminar Producto");
            alert1.setHeaderText("¿Está seguro de eliminar el producto?");
            alert1.setContentText("Esta acción no se puede deshacer");
            Optional<ButtonType> result = alert1.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    if (Eliminar.EliminarProductoID(Integer.parseInt(producto.getID()))) {
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Producto Eliminado", "Producto Eliminado");
                    } else {
                        mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el producto");
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "ID de producto no válido");
                }
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cancelado", "Eliminación Cancelada");
            }
            VerInventario.cargarInventarioTabla(NombreProductoColumn, DescripcionColumn, CantidadColumn, PrecioVentaUSDColumn, PrecioVentaBsColumn, TablaInventario, IDColumn);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No tiene permisos para eliminar productos");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void ModificarProducto() throws Exception {
        if (isAdmin()) {
            Producto producto = TablaInventario.getSelectionModel().getSelectedItem();

            if (producto == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un producto para Modificar");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modificar Producto");
            alert.setHeaderText("Qué quieres modificar?");

            Button btnStock = new Button("Stock");
            Button btnProducto = new Button("Producto");

            VBox dialogPaneContent = new VBox();
            dialogPaneContent.getChildren().addAll(btnStock, btnProducto);
            alert.getDialogPane().setContent(dialogPaneContent);

            btnStock.setOnAction(event -> {
                Alert alertStock = new Alert(Alert.AlertType.CONFIRMATION);
                alertStock.setTitle("Seleccionar Cantidad");
                alertStock.setHeaderText("¿Cuántas unidades desea llevar?");

                TextField inputField = new TextField();
                inputField.setPromptText("Ingrese Cantidad");

                VBox dialogPaneContentStock = new VBox();
                dialogPaneContentStock.getChildren().add(inputField);
                alertStock.getDialogPane().setContent(dialogPaneContentStock);

                alertStock.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        String input = inputField.getText();
                        try {
                            double cantidad = Double.parseDouble(input);
                            if (Modificar.modificarStock(String.valueOf(producto.getID()), input)) {
                                mostrarAlerta(Alert.AlertType.INFORMATION, "Modificación Exitosa",
                                        "La cantidad de " + producto.getNombre() + " se modificó con éxito.");
                            }
                        } catch (NumberFormatException e) {
                            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ingrese un número válido.");
                        }
                    }
                });
            });

            btnProducto.setOnAction(event -> {
                try {
                     Modificar.BuscarProductoID(Integer.parseInt(producto.getID()));
                     Open.ModificarProducto(Modificar.BuscarProductoID(Integer.parseInt(producto.getID())));
                } catch (Exception e) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al modificar el producto: " + e.getMessage());
                }
            });

            alert.showAndWait();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No tiene permisos para modificar productos");
        }
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario, IDColumn);
    }



    private void KeyPressed() {
        BuscarProductoTXT.addEventFilter(KeyEvent.KEY_RELEASED, event -> VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario,BuscarProductoTXT.getText(),IDColumn));
    }

    @FXML
    public void initialize(User user) {
        this.user = user;
        NombreUsuario = String.format("%s-[%s]", user.getUsername(), user.getRol());
        VerInventario.setNombreUsuario(NombreUsuario);
        KeyPressed();
        Dolar.ActualizarDolar();
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario, IDColumn);
    }

}



