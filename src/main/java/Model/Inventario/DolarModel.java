package Model.Inventario;

import Utils.ConectBD;
import Utils.GetCurrency;
import javafx.scene.control.Alert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DolarModel {
    ConectBD Conect = new ConectBD();
    GetCurrency Get = new GetCurrency();

    public void ActualizarDolar() {
        String Consulta = "EXEC Inventario.ActualizarPrecios @Nombre = ?, @NuevoPrecio = ?";

        try (Connection con = Conect.Conect();
             CallableStatement callable = con.prepareCall(Consulta)) {

            callable.setString(1, "Dolar BCV");
            callable.setDouble(2, Get.getCurrency().bcv());

            callable.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización de precios");
            alert.setHeaderText("Se actualizó el precio de la moneda : " + Get.getCurrency().bcv());
            alert.showAndWait();
            return;
        }catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No actualizó el precio de la moneda : " + e);
            alert.showAndWait();
        }
    }

    public String GetTasa() {
        String sql = "SELECT Valor FROM Inventario.Moneda WHERE Nombre = ?";
        String valor = null; // Variable para almacenar el valor de la tasa

        try (Connection con = Conect.Conect();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Dolar BCV"); // Establece el parámetro en la consulta

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) { // Mueve el cursor al primer registro
                    valor = rs.getString("Valor"); // Obtiene el valor de la columna "Valor"
                } else {
                    // Si no hay resultados, puedes manejarlo aquí
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al obtener la tasa");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        return valor; // Retorna el valor de la tasa (o null si no se encontró)
    }

}
