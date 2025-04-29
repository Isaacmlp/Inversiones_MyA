package Controller.Cliente;

import Model.Cliente.Cliente;
import Model.Cliente.EliminarClienteModel;
import Model.Cliente.ModificarClienteModel;
import Model.Cliente.VerClientesModel;
import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.Optional;




public class VerClientesController {
    VerClientesModel VerClientes = new VerClientesModel();
    User user;
    OpenView Open = new OpenView();
    EliminarClienteModel Eliminar = new EliminarClienteModel();
    ModificarClienteModel Modificar = new ModificarClienteModel();

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private TextField BuscarClienteTxT;

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
    void AgregarClienteBtn() throws Exception {
        Open.AgregarClientes();
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1,  IDColumn, TablaClientes);

    }

    @FXML
    void DescargarClientesBtn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("En Construcción");
        alert.setHeaderText(null);
        alert.setContentText("Estamos trabajando en este elemento");
        alert.showAndWait();
    }

    @FXML
    void EliminarClienteBtn() throws Exception {
        if (isAdmin()) {
            Cliente cliente = TablaClientes.getSelectionModel().getSelectedItem();
            if (cliente == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un cliente para Eliminar");
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Cliente");
            alert.setHeaderText("¿Está seguro de eliminar el cliente?");
            alert.setContentText("Esta acción no se puede deshacer");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    if (Eliminar.EliminarCliente(cliente.getCedula())) {
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Cliente Eliminado", "Cliente Eliminado");
                    } else {
                        mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el cliente");
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Cedula de cliente no válido");
                }
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cancelado", "Eliminación Cancelada");
            }
        }
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1,  IDColumn, TablaClientes);
    }

    @FXML
    void ModificarClienteBtn() throws Exception {
        if (isAdmin()) {
            Cliente cliente = TablaClientes.getSelectionModel().getSelectedItem();
            if (cliente == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un cliente para Modificar");
                return;
            }
            Open.ModificarClientes(Modificar.BuscarCliente(cliente.getCedula()));
        }
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1,  IDColumn, TablaClientes);
    }

    private boolean isAdmin() {
       return user.getRol().equals("Administrador") || user.getRol().equals("Supervisor");
    }

    private void KeyPressed() {
        BuscarClienteTxT.addEventFilter(KeyEvent.KEY_RELEASED, event -> VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1,  IDColumn, TablaClientes, BuscarClienteTxT.getText()));
    }


    @FXML
    public void initialize(User user) {
        this.user = user;
        KeyPressed();
        VerClientes.cargarClientesTabla(NombreClienteColumn, ApellidoClienteColumn, CedulaClienteColumn, TelefonoClienteColumn, DireccionClienteColumn, CorreoClienteColumn1,  IDColumn, TablaClientes);
    }

}
