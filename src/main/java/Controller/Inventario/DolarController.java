package Controller.Inventario;

import Model.Inventario.DolarModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DolarController {
    DolarModel Dolar = new DolarModel();


    @FXML
    private Label TasaTXT;

    @FXML
    void initialize() {
        TasaTXT.setText(Dolar.GetTasa());
    }

}
