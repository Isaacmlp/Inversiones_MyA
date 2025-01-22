package Controller;

import Utils.OpenView;
import javafx.fxml.FXML;

public class DashboardController {
    OpenView Open = new OpenView();

    @FXML
    void Inventario() throws Exception {
        Open.DashboardInventario();
    }

    @FXML
    void Clientes() throws Exception {
        Open.DashboardClientes();
    }

    @FXML
    void Facturar() throws Exception {
        Open.DashboardFacturar();
    }

    @FXML
    void Facturas() throws Exception {
        Open.DashboardFacturas();
    }

    @FXML
    void Monedas() throws Exception {
        Open.DashboardMonedas();
    }
}
