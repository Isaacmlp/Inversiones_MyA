package Utils;

import Controller.Cliente.DashboardClientesController;
import Controller.DashboardController;
import Controller.Facturar.DashboardFacturarController;
import Controller.Facturar.PagarController;
import Controller.Facturas.DasboardFacturasController;
import Controller.Facturas.VerFacturaCotroller;
import Controller.Inventario.DashboardInventarioController;
import Controller.Inventario.ElegirProductoController;
import Controller.Inventario.VerInventarioController;
import Model.Facturar.DashboardFacturarModel;
import Model.Facturas.FacturasModel;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OpenView {

    public void Login() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.Login));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.LoginCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void Dashboard(TextField btn, User user) throws Exception {
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.Dashboard));
        AnchorPane pane = loader.load();

        DashboardController DashboardController = loader.getController();
        DashboardController.initialize(user);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardCSS)).toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void AddProduct() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.AddProduct));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.ProductsCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardInventario(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardInventario));
        AnchorPane pane = loader.load();
        DashboardInventarioController DashboardInventarioController = loader.getController();
        DashboardInventarioController.initialize(user);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardInventarioCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void ModificarProducto() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.ModificarProducto));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.ModificarProductoCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void EliminarProducto() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.EliminarProducto));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.EliminarProductoCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void VerInventario(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VerInventario));
        AnchorPane pane = loader.load();

        VerInventarioController VerInventarioController = loader.getController();
        VerInventarioController.initialize(user);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.VerInventarioCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void BuscarProducto() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.BuscarProducto));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.BuscarProductoCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void Dolar() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.Dolar));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DolarCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardMonedas() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardMonedas));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardMonedasCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardFacturar(User user) throws Exception {
        DashboardFacturarModel DashboardFacturarmodel = new DashboardFacturarModel();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardFacturar));
        AnchorPane pane = loader.load();

        DashboardFacturarController FacturarController = loader.getController();
        FacturarController.initialize(DashboardFacturarmodel,user);


        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardFacturarCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardFacturas(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardFacturas));
        AnchorPane pane = loader.load();

        DasboardFacturasController DashboardFacturasController = loader.getController();
        DashboardFacturasController.initialize(user);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardFacturasCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardClientes(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardClientes));
        AnchorPane pane = loader.load();

        DashboardClientesController DashboardClientesController = loader.getController();
        DashboardClientesController.initialize(user);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void AgregarClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.AgregarClientes));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.AgregarClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void ModificarClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.ModificarClientes));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.ModificarClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void BuscarClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.BuscarClientes));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.BuscarClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void EliminarClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.EliminarClientes));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.EliminarClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void VerClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VerClientes));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.VerClientesCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void ElegirProducto(DashboardFacturarModel dashboardFacturarmodel) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.ElegirProducto));
        AnchorPane pane = loader.load();

        // Obtener el controlador de la nueva ventana
        ElegirProductoController elegirProductoController = loader.getController();
        elegirProductoController.initialize(dashboardFacturarmodel);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.ElegirProductoCSS)).toExternalForm());
        stage.setScene(scene);

        // Configurar el evento de cierre

        stage.show();
    }

    public void Pagar(DashboardFacturarModel DashboardFacturarModel) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.Pagar));
                AnchorPane pane = loader.load();

                PagarController PagarController = loader.getController();
                PagarController.initialize(DashboardFacturarModel);

                Scene scene = new Scene(pane);
                Stage stage = new Stage();

                String cssPath = Objects.requireNonNull(getClass().getResource(Paths.PagarCSS)).toExternalForm();
                if (cssPath != null) {
                    scene.getStylesheets().add(cssPath);
                } else {
                    System.err.println("CSS file not found: " + Paths.PagarCSS);
                }

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar el archivo: " + e.getMessage());
                alert.showAndWait();
            }
    }

    public void VerFacturas(FacturasModel facturas) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VerFacturas));
        AnchorPane pane = loader.load();

        VerFacturaCotroller VerFacturasController = loader.getController();
        VerFacturasController.initialize(facturas);
        VerFacturasController.setIdFactura(facturas.getIdFactura());


        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.VerFacturasCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
