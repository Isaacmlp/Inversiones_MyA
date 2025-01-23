package Controller.Cliente;

import Utils.OpenView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class DashboardClientesController {
    OpenView Open = new OpenView();


    @FXML
    void AgregarCliente(MouseEvent event) throws Exception {
        Open.AgregarClientes();
    }

    @FXML
    void BuscarCliente(MouseEvent event) {

    }

    @FXML
    void EliminarCliente(MouseEvent event) {

    }

    @FXML
    void ModificarCliente(MouseEvent event) throws Exception {
        Open.ModificarClientes();
    }

    @FXML
    void VerCliente(MouseEvent event) {

    }

}
