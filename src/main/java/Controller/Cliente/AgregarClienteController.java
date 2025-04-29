package Controller.Cliente;

import Model.Cliente.AgregarClienteModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarClienteController {
    AgregarClienteModel model = new AgregarClienteModel();

    @FXML
    private TextField ApellidoTXT;

    @FXML
    private TextField CedulaTXT;

    @FXML
    private TextField CorreoTXT;

    @FXML
    private TextField DireccionTXT;


    @FXML
    private TextField NombreTXT;

    @FXML
    private TextField TelefonoTXT;

    private boolean IsEmpty () {
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
    void BtnGuardar(ActionEvent event) {
        if (IsEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Debe llenar todos los campos");
            alert.showAndWait();
            return;
        }
        GuardarCliente();
    }

    private void GuardarCliente() {
        if (model.GuardarCliente(NombreTXT.getText(), ApellidoTXT.getText(), CedulaTXT.getText(), TelefonoTXT.getText(), DireccionTXT.getText(), CorreoTXT.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Agregado!");
            alert.setHeaderText(null);
            alert.setContentText("El cliente ha sido agregado");
            alert.showAndWait();
            CleanFields();
            ((Stage) NombreTXT.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar cliente");
            alert.showAndWait();
        }
    }
}
