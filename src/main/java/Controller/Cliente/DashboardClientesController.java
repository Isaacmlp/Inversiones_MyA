package Controller.Cliente;

import Utils.OpenView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class DashboardClientesController {
    OpenView open = new OpenView();


    @FXML
    void AgregarCliente(MouseEvent event) throws Exception {
        open.AgregarClientes();
    }

    @FXML
    void BuscarCliente(MouseEvent event) {

    }

    @FXML
    void EliminarCliente(MouseEvent event) {

    }

    @FXML
    void ModificarCliente(MouseEvent event) {

    }

    @FXML
    void VerCliente(MouseEvent event) {

    }

}
