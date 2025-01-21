package Controller;

import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardInventarioController {
    OpenView Open = new OpenView();


    @FXML
    void AgregarProducto(MouseEvent event) throws Exception {
        Open.AddProduct();
    }

}
