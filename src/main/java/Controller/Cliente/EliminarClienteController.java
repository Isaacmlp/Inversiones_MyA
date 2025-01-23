package Controller.Cliente;

import Model.Cliente.EliminarClienteModel;
import Model.Cliente.ModificarClienteModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class EliminarClienteController {
    ModificarClienteModel ModificarCliente = new ModificarClienteModel();
    EliminarClienteModel EliminarCliente = new EliminarClienteModel();

    @FXML
    private Label ApellidoTXT;

    @FXML
    private TextField BuscarTXT;

    @FXML
    private Label CedulaTXT;

    @FXML
    private Label CorreoTXT;

    @FXML
    private Label DireccionTXT;

    @FXML
    private Label NombreTXT;

    @FXML
    private Label TelefonoTXT;

    @FXML
    void BtnBuscar() {
        if (BuscarTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe rellenar el campo de búsqueda");
            alert.showAndWait();
            return;
        }
        BuscarCliente();
    }
    public void BuscarCliente() {
        ArrayList<String> Cliente = ModificarCliente.BuscarCliente(BuscarTXT.getText());

        if (!Cliente.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Buscado!");
            alert.setHeaderText(null);
            alert.setContentText("El cliente ha sido encontrado con exito");
            alert.showAndWait();
            CargarDataCliente(Cliente);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Ha ocurrido un error al buscar el cliente");
            alert.showAndWait();
        }
    }

    private void CargarDataCliente(ArrayList<String> Cliente) {
        NombreTXT.setText(Cliente.getFirst());
        ApellidoTXT.setText(Cliente.get(1));
        CedulaTXT.setText(Cliente.get(2));
        TelefonoTXT.setText(Cliente.get(3));
        DireccionTXT.setText(Cliente.get(4));
        CorreoTXT.setText(Cliente.get(5));
    }

    @FXML
    void BtnEliminar() {
        if (EliminarCliente.EliminarCliente(CedulaTXT.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Eliminado!");
            alert.setHeaderText(null);
            alert.setContentText("El cliente ha sido eliminado con exito");
            alert.showAndWait();
            CleanFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Ha ocurrido un error al eliminar el cliente");
            alert.showAndWait();
        }
    }

    private void CleanFields() {
        NombreTXT.setText("");
        ApellidoTXT.setText("");
        CedulaTXT.setText("");
        TelefonoTXT.setText("");
        DireccionTXT.setText("");
        CorreoTXT.setText("");
    }
}
