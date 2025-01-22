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
    void Clientes(MouseEvent event) throws Exception {
        Open.DashboardClientes();
    }

    @FXML
    void Facturar(MouseEvent event) throws Exception {
        Open.DashboardFacturar();
    }

    @FXML
    void Facturas(MouseEvent event) throws Exception {
        Open.DashboardFacturas();
    }

    @FXML
    void Monedas(MouseEvent event) throws Exception {
        Open.DashboardMonedas();
    }
}
