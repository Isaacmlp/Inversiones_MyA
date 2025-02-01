package Controller;

import Model.User;
import Utils.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import Service.AutenticacionService;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {
    AutenticacionService Auth = new AutenticacionService();
    OpenView Open = new OpenView();
    private String Clave = "";
    private String ClaveOculta = "";
    User user = new User();

    @FXML
    private TextField Userlbl;

    @FXML
    private TextField passwordlbl;

    private void Alerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void Login(String usuario, String password) throws Exception {

        if (Auth.Login(usuario, password)) {
            int rol = Auth.GetID(usuario);
            String mensaje;
            if (Auth.tieneRol(rol, "Administrador")) {
                mensaje = "Bienvenido de Nuevo, " + usuario + " [Administrador]";
                user.setRol("Administrador");
                user.setUsername(usuario);
            } else if (Auth.tieneRol(rol, "Supervisor")) {
                mensaje = "Bienvenido de Nuevo, " + usuario + " [Supervisor]";
                user.setRol("Supervisor");
                user.setUsername(usuario);
            } else if (Auth.tieneRol(rol, "Empleado")) {
                mensaje = "Bienvenido de Nuevo, " + usuario + " [Empleado]";
                user.setRol("Empleado");
                user.setUsername(usuario);
            } else {
                mensaje = "Rol Indefinido";
                Alerta("Error", mensaje, Alert.AlertType.ERROR);
                return;
            }
            Alerta("Login exitoso", mensaje, Alert.AlertType.INFORMATION);
            Open.Dashboard(Userlbl,user);
        } else {
            Alerta("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }

    private boolean isEmpty() {
        return (Userlbl.getText().isEmpty() || passwordlbl.getText().isEmpty());
    }

    @FXML
    void BtnLogin() throws Exception {
        if (isEmpty()) {
            Alerta("Error", "Debe rellenar los campos de usuario y contraseña",Alert.AlertType.ERROR);
        } else {
            if (Clave.isEmpty()){
                Login(Userlbl.getText(), passwordlbl.getText());

            } else {
                Login(Userlbl.getText(), Clave);
            }
        }
    }

    private void AddKeyListeners() {
        Userlbl.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    BtnLogin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        passwordlbl.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    BtnLogin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private String OcultarPassword(String texto) {
        return "*".repeat(texto.length());
    }

    @FXML
    void OcultarContrasena() {
        if (passwordlbl.getText().equals(ClaveOculta)) {
            passwordlbl.setText(Clave);
        } else {
            Clave = passwordlbl.getText();
            ClaveOculta = OcultarPassword(Clave);
            passwordlbl.setText(ClaveOculta);
        }
    }

    @FXML
    void initialize() {
        AddKeyListeners();
    }
}
