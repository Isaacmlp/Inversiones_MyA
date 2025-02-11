package Controller.Inventario;

import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class DashboardInventarioController {
    OpenView Open = new OpenView();
    User user;

    private boolean isAdmin() {
        return user.getRol().equals("Administrador");
    }

    @FXML
    void AgregarProducto() throws Exception {
        Open.AddProduct();
    }

    @FXML
    void Modificar() throws Exception {
        if (isAdmin()) {
           // Open.ModificarProducto();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para modificar productos");
            alert.showAndWait();
        }
    }

    @FXML
    void Eliminar() throws Exception {
        if (isAdmin()) {
            Open.EliminarProducto();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tiene permisos para eliminar productos");
            alert.showAndWait();
        }
    }

    @FXML
    void Ver() throws Exception {
        Open.VerInventario(user);
    }

    @FXML
    void Buscar() throws Exception {
        Open.BuscarProducto();
    }

    @FXML
    void TasaDolar() throws Exception {
        Open.Dolar();
    }

    @FXML
    public void initialize(User user) {
        this.user = user;
    }
}
