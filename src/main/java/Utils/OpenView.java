package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    public void Dashboard() throws Exception {
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
}
