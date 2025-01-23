package Aplicacion;

import Utils.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DashboardFacturar));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Paths.DashboardFacturarCSS)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
