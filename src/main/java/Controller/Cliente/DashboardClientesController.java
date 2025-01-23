package Controller.Cliente;

import Utils.OpenView;
import javafx.fxml.FXML;

public class DashboardClientesController {
    OpenView Open = new OpenView();


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
        Open.EliminarClientes();
    }

    @FXML
    void ModificarCliente() throws Exception {
        Open.ModificarClientes();
    }

    @FXML
    void VerCliente() {

    }

}
