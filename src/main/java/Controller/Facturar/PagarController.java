package Controller.Facturar;

import Model.Facturar.DashboardFacturarModel;
import Model.Facturar.PagarModel;
import Utils.GetCurrency;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class PagarController {
    DashboardFacturarModel dashboardFacturarModel;
    ArrayList<Double> Totales = new ArrayList<>();
    ArrayList<String> MetodosPago = new ArrayList<>();
    GetCurrency getCurrency = new GetCurrency();
    PagarModel Pagarmodel = new PagarModel();
    private double TotalUSD = 0.0;
    private double TotalBS = 0.0;

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

    private void Alert (String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void BtnPagar() {
      /* if (isEmpty()) {
            Alert("Error", "El campo de monto de pago no puede estar vacío", Alert.AlertType.ERROR);
            return;
        }

       if (MetodoPagoBs()) {
            // Obtener el monto pagado y los totales
            BigDecimal montoPagado = new BigDecimal(MontoPagadoTXT.getText()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalBs = new BigDecimal(Totales.get(1).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalUSDs = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
            AddMetodoPago(montoPagado);


           if (VerificarTotales(TotalBS, TotalUSD)) {
               TotalUSD = totalUSDs.doubleValue();
               TotalBS = totalBs.doubleValue();
           }

            BigDecimal montoPagadoUSD = montoPagado.divide(BigDecimal.valueOf(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
            BigDecimal totalUSD = new BigDecimal(Totales.getFirst().toString()).setScale(2, RoundingMode.HALF_UP);
            totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD.doubleValue());

            totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(1, totalBs.doubleValue());

            // Actualizar las etiquetas
            TotalUSDTXT.setText(totalUSD + " $");
            TotalBsTXT.setText(totalBs + " Bs");

            // Verificar si el pago fue exitoso
           Alert("Pendiente", " Pendiente por Pagar: " + totalBs + " bs", Alert.AlertType.CONFIRMATION);


           if (totalBs.doubleValue() == 0.00) {
               if (PagarFactura()) {
                   mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                   ((Stage) TotalBsTXT.getScene().getWindow()).close();
                   dashboardFacturarModel.setCleanALL(true);
               } else {
                   mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
               }
           }
       } else if (MetodoPagoUSD()) {
            // Obtener el monto pagado y los totales
            BigDecimal montoPagado = new BigDecimal(MontoPagadoTXT.getText()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalBs = new BigDecimal(Totales.get(1).toString()).setScale(2, RoundingMode.HALF_UP);
            AddMetodoPago(montoPagado);

            if (VerificarTotales(TotalBS, TotalUSD)) {
                TotalUSD = totalUSD.doubleValue();
                TotalBS = totalBs.doubleValue();
            }

            BigDecimal totalUSD2 = new BigDecimal(Totales.getFirst().toString()).setScale(2, RoundingMode.HALF_UP);
            totalUSD2 = totalUSD2.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD2.doubleValue());


            montoPagado = montoPagado.multiply(BigDecimal.valueOf(getCurrency.getCurrency().bcv()));
            totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(1, totalBs.doubleValue());

            // Actualizar las etiquetas
            TotalUSDTXT.setText(totalUSD2 + " $");
            TotalBsTXT.setText(totalBs + " Bs");

            // Verificar si el pago fue exitoso
           Alert("Pendiente", " Pendiente por Pagar: " + totalUSD2 + " bs", Alert.AlertType.CONFIRMATION);

           if (totalUSD2.doubleValue() == 0.00) {
               if (PagarFactura()) {
                   mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                   ((Stage) TotalBsTXT.getScene().getWindow()).close();
                   dashboardFacturarModel.setCleanALL(true);
               } else {
                   mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
               }
           }
       }*/

        if (isEmpty()) {
            Alert("Error", "El campo de monto de pago no puede estar vacío", Alert.AlertType.ERROR);
            return;
        }

        BigDecimal montoPagado = new BigDecimal(MontoPagadoTXT.getText()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalBs = new BigDecimal(Totales.get(1).toString()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
        AddMetodoPago(montoPagado);

        if (VerificarTotales(TotalBS, TotalUSD)) {
            TotalUSD = totalUSD.doubleValue();
            TotalBS = totalBs.doubleValue();
        }

        if (MetodoPagoBs()) {
            BigDecimal montoPagadoUSD = montoPagado.divide(BigDecimal.valueOf(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
            totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD.doubleValue());

            totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(1, totalBs.doubleValue());
        } else if (MetodoPagoUSD()) {
            totalUSD = totalUSD.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD.doubleValue());

            montoPagado = montoPagado.multiply(BigDecimal.valueOf(getCurrency.getCurrency().bcv()));
            totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
            Totales.set(1, totalBs.doubleValue());
        }

        // Actualizar las etiquetas
        TotalUSDTXT.setText(totalUSD + " $");
        TotalBsTXT.setText(totalBs + " Bs");

        // Verificar si el pago fue exitoso

        if ((MetodoPagoBs() && totalBs.doubleValue() == 0.00) || (MetodoPagoUSD() && totalUSD.doubleValue() == 0.00)) {
            if (PagarFactura()) {
                mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                ((Stage) TotalBsTXT.getScene().getWindow()).close();
                dashboardFacturarModel.setCleanALL(true);
            } else {
                mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
            }
        } else {
            Alert("Pendiente", "Pendiente por Pagar: " + (MetodoPagoBs() ? (totalBs + " Bs") : totalUSD + " $"), Alert.AlertType.CONFIRMATION);
        }
    }

    private void AddMetodoPago(BigDecimal montoPagado) {
        MetodosPago.add(ComboMetodoPago.getValue());
        MetodosPago.add(montoPagado.toString());
        MetodosPago.add(NroReferenciaTXT.getText());
    }

    private boolean MetodoPagoBs() {
        return Objects.equals(ComboMetodoPago.getValue(), "Pago Movil") ||
                Objects.equals(ComboMetodoPago.getValue(), "Transferencia Bancaria") ||
                Objects.equals(ComboMetodoPago.getValue(), "Efectivo Bolivares") ||
                Objects.equals(ComboMetodoPago.getValue(), "Biopago") ||
                Objects.equals(ComboMetodoPago.getValue(), "Punto de Venta");
    }

    private boolean MetodoPagoUSD() {
        return Objects.equals(ComboMetodoPago.getValue(), "Efectivo USD") ||
                Objects.equals(ComboMetodoPago.getValue(), "Zinli") ||
                Objects.equals(ComboMetodoPago.getValue(), "Binance");
    }

    private boolean VerificarTotales(Double totalBs, Double totalUSD) {
        return totalBs == 0.00 && totalUSD == 0.00;
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public boolean PagarFactura() {
        return Pagarmodel.insertarFacturaCompleta(dashboardFacturarModel.getIDCliente(), LocalDate.now().toString(), TotalBS,TotalUSD ,"Pagado", Pagarmodel.setDetallesFactura(dashboardFacturarModel.GetProducto()), Pagarmodel.setMetodosPago(MetodosPago));
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
        ComboMetodoPago.getItems().addAll("Efectivo Bolivares", "Efectivo USD", "Pago Movil", "Transferencia Bancaria", "Zinli", "Binance","Biopago","Punto de Venta");
        Visible(false);
        Totales = dashboardFacturarModel.ObtenerTotales();
        TotalUSDTXT.setText(Totales.get(0).toString() + " $");
        TotalBsTXT.setText(Totales.get(1).toString() + " Bs");
    }
}
