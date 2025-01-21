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

    @FXML
    void Modificar(MouseEvent event) throws Exception {
        Open.ModificarProducto();
    }

    @FXML
    void Eliminar(MouseEvent event) throws Exception {
        Open.EliminarProducto();
    }

    @FXML
    void Ver(MouseEvent event) throws Exception {
        Open.VerInventario();
    }

    @FXML
    void Buscar(MouseEvent event) throws Exception {
        Open.BuscarProducto();
    }



}
