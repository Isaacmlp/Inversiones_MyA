package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void Dashboard(TextField btn) throws Exception {
        btn.getScene().getWindow().getOnCloseRequest();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.Dashboard));
        AnchorPane pane = loader.load();
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

    public void DashboardInventario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardInventario));
        AnchorPane pane = loader.load();
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

    public void VerInventario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VerInventario));
        AnchorPane pane = loader.load();
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

    public void DashboardFacturar() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardFacturar));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardFacturarCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardFacturas() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardFacturas));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardFacturasCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void DashboardClientes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardClientes));
        AnchorPane pane = loader.load();
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

}
