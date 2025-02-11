package Controller;

import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController {
    OpenView Open = new OpenView();
    User user;

    private boolean isAdmin() {
        return user.getRol().equals("Administrador") || user.getRol().equals("Supervisor");
    }

    @FXML
    private AnchorPane archonPane;

    @FXML
    void Inventario() throws Exception {
        Open.VerInventario(user);
    }

    @FXML
    void Clientes() throws Exception {
        Open.DashboardClientes(user);
    }

    @FXML
    void Facturar() throws Exception {
        Open.DashboardFacturar(user);
    }

    @FXML
    void Facturas() throws Exception {
        if (isAdmin()) {
            Open.DashboardFacturas(user);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para ver facturas");
            alert.showAndWait();
        }
    }

    private void EnterFacturas() throws Exception {
        if (isAdmin()) {
            Open.DashboardFacturas(user);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para ver facturas");
            alert.showAndWait();
        }
    }

    @FXML
    public void ActionPane(javafx.scene.input.KeyEvent keyEvent) throws Exception {
        switch (keyEvent.getCode()) {
            case E:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardFacturar(user);
                break;
            case F:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) EnterFacturas();
                break;
            case M:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardMonedas();
                break;
            case C:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.DashboardClientes(user);
                break;
            case I:
                if (keyEvent.isControlDown() || keyEvent.isAltDown()) Open.VerInventario(user);
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
    public void initialize(User user) {
        this.user = user;
        archonPane.setFocusTraversable(true);
        archonPane.requestFocus();
    }
}
