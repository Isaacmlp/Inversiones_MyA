package Controller.Cliente;

import Model.Cliente.Cliente;
import Model.Cliente.VerClientesModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VerClientesController {
    VerClientesModel VerClientes = new VerClientesModel();

    @FXML
    private TableColumn<Cliente, String> ApellidoClienteColumn;

    @FXML
    private TableColumn<Cliente, String> CedulaClienteColumn;

    @FXML
    private TableColumn<Cliente, String> CorreoClienteColumn1;

    @FXML
    private TableColumn<Cliente, String> DireccionClienteColumn;

    @FXML
    private TableColumn<Cliente, String> NombreClienteColumn;

    @FXML
    private TableView<Cliente> TablaClientes;

    @FXML
    private TableColumn<Cliente, String> TelefonoClienteColumn;

    @FXML
    void initialize() {
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1, TablaClientes);
    }

}
