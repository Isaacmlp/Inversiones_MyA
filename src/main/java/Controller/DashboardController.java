package Controller;

import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.awt.event.KeyEvent;

public class DashboardController {
    OpenView Open = new OpenView();

    @FXML
    private AnchorPane archonPane;

    @FXML
    private TitledPane titlePane;

    @FXML
    void Inventario() throws Exception {
        Open.DashboardInventario();
    }

    @FXML
    void Clientes() throws Exception {
        Open.DashboardClientes();
    }

    @FXML
    void Facturar() throws Exception {
        Open.DashboardFacturar();
    }

    @FXML
    void Facturas() throws Exception {
        Open.DashboardFacturas();
    }

    @FXML
    void Monedas() throws Exception {
        Open.DashboardMonedas();
    }

    private void KeyPressed(KeyEvent event) throws Exception {
        if (event.getSource() == KeyCode.F) {
            Open.DashboardFacturas();
        }
    }

    @FXML
    public void ActionPane(javafx.scene.input.KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Open.DashboardFacturas();
        } else if (keyEvent.getCode() == KeyCode.F) {
            Open.DashboardFacturas();
        } else if (keyEvent.getCode() == KeyCode.M) {
            Open.DashboardMonedas();
        } else if (keyEvent.getCode() == KeyCode.C) {
            Open.DashboardClientes();
        } else if (keyEvent.getCode() == KeyCode.A) {
            Open.DashboardInventario();
        }
    }

    @FXML
    public void TitleAction(javafx.scene.input.KeyEvent keyEvent) throws Exception {
    }


    @FXML
    void initialize () throws Exception {
        archonPane.setFocusTraversable(true);

        // Establecer el foco en el ArchonPane al iniciar
        archonPane.requestFocus();

    }


}
