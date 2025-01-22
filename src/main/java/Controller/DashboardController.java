package Controller;

import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardController {
    OpenView Open = new OpenView();

    @FXML
    void Inventario(MouseEvent event) throws Exception {
        Open.DashboardInventario();
    }

    @FXML
    void Clientes(MouseEvent event) {

    }

    @FXML
    void Facturar(MouseEvent event) {

    }

    @FXML
    void Facturas(MouseEvent event) {

    }

    @FXML
    void Monedas(MouseEvent event) {

    }
}
