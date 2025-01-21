package Controller;

import Utils.OpenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import Service.AutenticacionService;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {
    AutenticacionService Auth = new AutenticacionService();
    OpenView Open = new OpenView();

    @FXML
    private ImageView ImageViewLogin;

    @FXML
    private TextField Userlbl;

    @FXML
    private TextField passwordlbl;

    private void Login(String usuario, String password) throws Exception {
        Auth = new AutenticacionService();
        if (Auth.Login(usuario, password)) {
            if (Auth.tieneRol(Auth.GetID(usuario), "Administrador")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido de Nuevo, " + usuario + " [Administrador]");
                alert.showAndWait();
                Open.Dashboard();
            } else if (Auth.tieneRol(Auth.GetID(usuario), "Supervisor")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido de Nuevo, " + usuario + " [Supervisor]");
                alert.showAndWait();
                Open.Dashboard();

            } else if (Auth.tieneRol(Auth.GetID(usuario), "Empleado")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido de Nuevo, " + usuario + " [Empleado]");
                alert.showAndWait();
                Open.Dashboard();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido de Nuevo, " + usuario + " [Indefinido]");
                alert.showAndWait();
                Open.Dashboard();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
        }

    }


    @FXML
    void BtnLogin(ActionEvent event) throws Exception {

        if (Userlbl.getText().isEmpty() || passwordlbl.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe rellenar los campos de usuario y contraseña");
            alert.showAndWait();
        } else {
            Login(Userlbl.getText(), passwordlbl.getText());
        }
    }



    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                System.out.println("Se presionó ENTER");
                break;
            case ESCAPE:
                System.out.println("Se presionó ESCAPE");
                break;
            case A:
                if (event.isControlDown()) {
                    System.out.println("Se presionó CTRL + A");
                }
                break;
            default:
                System.out.println("Tecla presionada: " + event.getCode());
                break;
        }
    }


    void intialize() {
        Userlbl.setOnKeyPressed(this::handleKeyPress);

    }
}
