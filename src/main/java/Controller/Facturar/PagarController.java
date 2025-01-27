package Controller.Facturar;

import Model.Facturar.DashboardFacturarModel;
import Utils.GetCurrency;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class PagarController {
    DashboardFacturarModel dashboardFacturarModel;
    ArrayList<Double> Totales = new ArrayList<>();
    ArrayList<String> MetodosPago = new ArrayList<>();
    Double MontoPagado = 0.0;
    GetCurrency getCurrency = new GetCurrency();

    @FXML
    private ComboBox<String> ComboMetodoPago;

    @FXML
    private TextField MontoPagadoTXT;

    @FXML
    private Label TotalBsTXT;

    @FXML
    private Label NroReferenciaLbL;

    @FXML
    private TextField NroReferenciaTXT;

    @FXML
    private Label TotalUSDTXT;

    @FXML
    void BtnPagar() {
        if (isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha introducido el monto de pago");
            alert.setContentText("El campo de monto de pago no puede estar vacío");
            alert.showAndWait();
            return;
        }

        Pagar();

        if (Objects.equals(MontoPagado, Totales.get(0))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pago Exitoso");
            alert.setHeaderText("El pago se ha realizado con exito");
            alert.setContentText("El monto de pago es igual al total de la factura");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pago");
            alert.setHeaderText("Pendiente");
            alert.setContentText("Pendiente por Pagar : " + Totales.get(1).toString() + " bs");
            alert.showAndWait();
        }

    }

    private void Pagar() {
        MontoPagado = Double.parseDouble(MontoPagadoTXT.getText());
        if (MontoPagado <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha introducido el monto de pago");
            alert.setContentText("El monto de pago no puede ser menor a cero");
            alert.showAndWait();
            return;
        }
        MetodosPago.add(ComboMetodoPago.getValue());
        MetodosPago.add(NroReferenciaTXT.getText());

        Totales.set(1, Totales.get(1) - MontoPagado );
        BigDecimal TotalBs = BigDecimal.valueOf(Totales.get(1));
        TotalBs = TotalBs.setScale(2, RoundingMode.HALF_UP);

        Totales.set(0, Totales.get(0) - (MontoPagado / getCurrency.getCurrency().bcv()));
        BigDecimal TotalUSD = BigDecimal.valueOf(Totales.getFirst());
        TotalUSD = TotalUSD.setScale(2, RoundingMode.HALF_UP);

        TotalUSDTXT.setText(TotalUSD.toString() + " $");
        TotalBsTXT.setText(TotalBs.toString() + " Bs");

    }

    private void Visible(boolean visible) {
        NroReferenciaTXT.setVisible(visible);
        NroReferenciaLbL.setVisible(visible);
    }

    @FXML
    void ComboMetodoPago(ActionEvent event) {
        if (event.getSource() == ComboMetodoPago) {
            switch (ComboMetodoPago.getValue()) {
                case "Pago Movil", "Transferencia Bancaria", "Zinli", "Binance" -> Visible(true);
                default -> Visible(false);
            }
        }
    }

    private boolean isEmpty() {
        return (MontoPagadoTXT.getText().isEmpty() || ComboMetodoPago.getValue() == null);
    }

    @FXML
    public void initialize(DashboardFacturarModel dashboardFacturarModel) {
        this.dashboardFacturarModel = dashboardFacturarModel;
        ComboMetodoPago.getItems().addAll("Efectivo Bolivares", "Efectivo USD", "Pago Movil", "Transferencia Bancaria", "Zinli", "Binance");
        Visible(false);
        Totales = dashboardFacturarModel.ObtenerTotales();
        TotalUSDTXT.setText(Totales.get(0).toString() + " $");
        TotalBsTXT.setText(Totales.get(1).toString() + " Bs");
    }
}
