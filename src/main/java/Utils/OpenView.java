package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class OpenView {

    public void Login() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PathLogin));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.PathLoginCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void Dashboard() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PathDashboard));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.PathDashboardCSS)).toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void AddProduct() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.PathAddProduct));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.PathProductsCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
