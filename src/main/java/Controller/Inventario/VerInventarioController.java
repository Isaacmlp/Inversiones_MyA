package Controller.Inventario;

import Model.Inventario.DolarModel;
import Model.Inventario.Producto;
import Model.Inventario.VerInventarioModel;
import Model.PDF;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import Model.FileChoose;
import Model.Inventario.Inventario;

import static Model.GenerateHTML.generarReporte;

public class VerInventarioController {
    VerInventarioModel VerInventario = new VerInventarioModel();
    DolarModel Dolar = new DolarModel();
    FileChoose FileChoose = new FileChoose();
    User user;
    String NombreUsuario;


    @FXML
    private Button BtnDescargar;

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

        Inventario reporte = VerInventario.GetAllInventario();
        if (reporte == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo generar el reporte");
            alert.showAndWait();
            return;
        }

        NombreUsuario = String.format("%s-[%s]", user.getUsername(), user.getRol());
        VerInventario.setNombreUsuario(NombreUsuario);
        String HTMl = generarReporte(reporte);
        PDF.generarPDF(HTMl, FileChoose.EscojerRuta(BtnDescargar, String.valueOf(VerInventario.GetAllInventario().getFecha())));
    }

    private void KeyPressed() {
        BuscarProductoTXT.addEventFilter(KeyEvent.KEY_RELEASED, event -> VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario,BuscarProductoTXT.getText()));
    }

    @FXML
    public void initialize(User user) {
        this.user = user;
        KeyPressed();
        Dolar.ActualizarDolar();
        VerInventario.cargarInventarioTabla(NombreProductoColumn,DescripcionColumn,CantidadColumn,PrecioVentaUSDColumn,PrecioVentaBsColumn,TablaInventario);
    }

}



