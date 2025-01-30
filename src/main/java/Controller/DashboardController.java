package Controller;

import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController {
    OpenView Open = new OpenView();

    @FXML
    private AnchorPane archonPane;

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
    public void ActionPane(javafx.scene.input.KeyEvent keyEvent) throws Exception {
        switch (keyEvent.getCode()) {
            case E:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardFacturar();
                break;
            case F:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardFacturas();
                break;
            case M:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardMonedas();
                break;
            case C:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardClientes();
                break;
            case I:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardInventario();
                break;
            case S:
            case ESCAPE:
                if (keyEvent.isControlDown() || keyEvent.isAltDown() || keyEvent.getCode() == KeyCode.ESCAPE) {
                    Open.Login();
                    ((Stage) archonPane.getScene().getWindow()).close();
                }
                break;
            default:
                break;
        }
    }

    @FXML
    void CerrarSesion() throws Exception {
        Open.Login();
        ((Stage) archonPane.getScene().getWindow()).close();
    }

    @FXML
    void initialize () {
        archonPane.setFocusTraversable(true);
        archonPane.requestFocus();
    }
}
