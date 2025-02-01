package Controller.Cliente;

import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class DashboardClientesController {
    OpenView Open = new OpenView();
    User user;

    private boolean isAdmin() {
        return user.getRol().equals("Administrador");
    }


    @FXML
    void AgregarCliente() throws Exception {
        Open.AgregarClientes();
    }

    @FXML
    void BuscarCliente() throws Exception {
        Open.BuscarClientes();
    }

    @FXML
    void EliminarCliente() throws Exception {
        if (isAdmin()) {
            Open.EliminarClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para eliminar clientes");
            alert.showAndWait();
        }

    }

    @FXML
    void ModificarCliente() throws Exception {
        if (isAdmin()) {
            Open.ModificarClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para modificar clientes");
            alert.showAndWait();
        }
    }

    @FXML
    void VerCliente() throws Exception {
        Open.VerClientes();
    }

    @FXML
    public void initialize(User user) {
        this.user = user;

    }

}
