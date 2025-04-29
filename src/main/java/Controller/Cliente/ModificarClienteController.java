package Controller.Cliente;

import Model.Cliente.ModificarClienteModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ModificarClienteController {
    ModificarClienteModel ModificarCliente = new ModificarClienteModel();
    private String ID;

    @FXML
    private TextField ApellidoTXT;

    @FXML
    private TextField  CedulaTXT;

    @FXML
    private TextField CorreoTXT;

    @FXML
    private TextField DireccionTXT;

    @FXML
    private TextField NombreTXT;

    @FXML
    private TextField TelefonoTXT;

    private boolean IsEmpty() {
        return NombreTXT.getText().isEmpty() || ApellidoTXT.getText().isEmpty() || CedulaTXT.getText().isEmpty() || TelefonoTXT.getText().isEmpty() || DireccionTXT.getText().isEmpty() || CorreoTXT.getText().isEmpty();
    }

    private void CleanFields() {
        NombreTXT.setText("");
        ApellidoTXT.setText("");
        CedulaTXT.setText("");
        TelefonoTXT.setText("");
        DireccionTXT.setText("");
        CorreoTXT.setText("");
    }

    @FXML
    void BtnGuardar() {
        if (IsEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Complete los campos vacios");
            alert.showAndWait();
            return;
        }
        ModificarCliente();
    }

    private void CargarDataCliente(ArrayList<String> Cliente) {
        NombreTXT.setText(Cliente.getFirst());
        ApellidoTXT.setText(Cliente.get(1));
        CedulaTXT.setText(Cliente.get(2));
        TelefonoTXT.setText(Cliente.get(3));
        DireccionTXT.setText(Cliente.get(4));
        CorreoTXT.setText(Cliente.get(5));
        ID = Cliente.get(6);
    }

    private void ModificarCliente() {
        if (ModificarCliente.ModificarCliente(NombreTXT.getText(), ApellidoTXT.getText(), CedulaTXT.getText(), TelefonoTXT.getText(), DireccionTXT.getText(), CorreoTXT.getText(), ID)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Modificado!");
            alert.setHeaderText(null);
            alert.setContentText("El cliente ha sido modificado con exito");
            alert.showAndWait();
            CleanFields();
            ((Stage) NombreTXT.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Ha ocurrido un error al modificar el cliente");
            alert.showAndWait();
        }
    }

    public void iniatialize(ArrayList<String> clientes) {
        CargarDataCliente(clientes);
    }
}
