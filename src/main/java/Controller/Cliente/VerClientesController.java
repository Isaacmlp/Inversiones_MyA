package Controller.Cliente;

import Model.Cliente.Cliente;
import Model.Cliente.VerClientesModel;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VerClientesController {
    VerClientesModel VerClientes = new VerClientesModel();
    User user;

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
    private TableColumn<Cliente, String> IDColumn;

    @FXML
    void AgregarClienteBtn() {

    }

    @FXML
    void DescargarClientesBtn() {

    }

    @FXML
    void EliminarClienteBtn() {

    }

    @FXML
    void ModificarClienteBtn() {

    }

    @FXML
    public void initialize(User user) {
        this.user = user;
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1, TablaClientes);
    }

}
