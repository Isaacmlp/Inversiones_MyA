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
import java.util.Optional;

public class PagarController {
    DashboardFacturarModel dashboardFacturarModel;
    ArrayList<Double> Totales = new ArrayList<>();
    ArrayList<String> MetodosPago = new ArrayList<>();
    Double MontoPagado = 0.0;
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
            TotalUSDTXT.setText(totalUSD.toString() + " $");
            TotalBsTXT.setText(totalBs.toString() + " Bs");

            // Verificar si el pago fue exitoso

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
            TotalUSDTXT.setText(totalUSD2.toString() + " $");
            TotalBsTXT.setText(totalBs.toString() + " Bs");

            // Verificar si el pago fue exitoso

           if (totalUSD2.doubleValue() == 0.00) {
               if (PagarFactura()) {
                   mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                   ((Stage) TotalBsTXT.getScene().getWindow()).close();
                   dashboardFacturarModel.setCleanALL(true);
               } else {
                   mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
               }
           }
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

    private void nul() {
        if (isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha introducido el monto de pago");
            alert.setContentText("El campo de monto de pago no puede estar vacío");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(ComboMetodoPago.getValue(), "Pago Movil") ||
                Objects.equals(ComboMetodoPago.getValue(), "Transferencia Bancaria") ||
                Objects.equals(ComboMetodoPago.getValue(), "Efectivo Bolivares") ||
                Objects.equals(ComboMetodoPago.getValue(), "Biopago") ||
                Objects.equals(ComboMetodoPago.getValue(), "Punto de Venta")) {

            // Obtener el monto pagado y los totales
            BigDecimal montoPagado = new BigDecimal(MontoPagadoTXT.getText()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalBs = new BigDecimal(Totales.get(1).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalUSDs = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);

            BigDecimal montoPagadoUSD = montoPagado.divide(new BigDecimal(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
            BigDecimal totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
            totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD.doubleValue());

            // Actualizar las etiquetas
            TotalUSDTXT.setText(totalUSD.toString() + " $");
            TotalBsTXT.setText(totalBs.toString() + " Bs");
            // Inicializar TotalBS y TotalUSD si es necesario
            if (TotalBS == 0.0 || TotalUSD == 0.0) {
                TotalBS = totalBs.doubleValue();
                TotalUSD = totalUSDs.doubleValue();
            }

            // Validar que el monto pagado no exceda el total en bolívares
            if (montoPagado.compareTo(totalBs) > 0) {
                mostrarAlerta("Error", "Monto excedido", "El monto pagado no puede ser mayor que el total pendiente en bolívares.", Alert.AlertType.ERROR);
                return; // Detener la ejecución si el monto es mayor
            }

            // Verificar si el monto pagado es igual al total en bolívares
            System.out.println(TotalBsTXT.getText().replace(" ", "").replace("Bs", ""));
            if (Double.parseDouble(TotalBsTXT.getText().replace(" ", "").replace("Bs", "")) == 0.00) {
                MetodosPago.add(ComboMetodoPago.getValue());
                MetodosPago.add(montoPagado.toString());
                MetodosPago.add(NroReferenciaTXT.getText());

                // Restar el monto pagado del total en bolívares
                totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
                Totales.set(1, totalBs.doubleValue());

                // Convertir el monto pagado a dólares y restarlo del total en dólares
                montoPagadoUSD = montoPagado.divide(new BigDecimal(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
                totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
                totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
                Totales.set(0, totalUSD.doubleValue());

                // Actualizar las etiquetas
                TotalUSDTXT.setText(totalUSD.toString() + " $");
                TotalBsTXT.setText(totalBs.toString() + " Bs");

                // Verificar si el pago fue exitoso
                if (PagarFactura()) {
                    mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                    ((Stage) TotalBsTXT.getScene().getWindow()).close();
                    dashboardFacturarModel.setCleanALL(true);
                } else {
                    mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
                }
            } else {
                // Si el monto pagado no es igual al total en bolívares
                MetodosPago.add(ComboMetodoPago.getValue());
                MetodosPago.add(montoPagado.toString());
                MetodosPago.add(NroReferenciaTXT.getText());

                // Restar el monto pagado del total en bolívares
                totalBs = totalBs.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
                Totales.set(1, totalBs.doubleValue());

                // Convertir el monto pagado a dólares y restarlo del total en dólares
                montoPagadoUSD = montoPagado.divide(new BigDecimal(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
                totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
                totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
                Totales.set(0, totalUSD.doubleValue());

                // Actualizar las etiquetas
                TotalUSDTXT.setText(totalUSD.toString() + " $");
                TotalBsTXT.setText(totalBs.toString() + " Bs");

                // Mostrar alerta de pago pendiente
                mostrarAlerta("Pago Pendiente", "Pendiente por Pagar", "Pendiente por Pagar: " + totalBs.toString() + " bs", Alert.AlertType.CONFIRMATION);
            }
        } else if (Objects.equals(ComboMetodoPago.getValue(), "Efectivo USD") ||
                Objects.equals(ComboMetodoPago.getValue(), "Zinli") ||
                Objects.equals(ComboMetodoPago.getValue(), "Binance")) {

            // Obtener el monto pagado y los totales
            BigDecimal montoPagado = new BigDecimal(MontoPagadoTXT.getText()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalBs = new BigDecimal(Totales.get(1).toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal montoPagadoUSD = montoPagado.divide(new BigDecimal(getCurrency.getCurrency().bcv()), 2, RoundingMode.HALF_UP);
            totalUSD = new BigDecimal(Totales.get(0).toString()).setScale(2, RoundingMode.HALF_UP);
            totalUSD = totalUSD.subtract(montoPagadoUSD).setScale(2, RoundingMode.HALF_UP);
            Totales.set(0, totalUSD.doubleValue());

            // Actualizar las etiquetas
            TotalUSDTXT.setText(totalUSD.toString() + " $");
            TotalBsTXT.setText((totalBs.doubleValue() - Double.parseDouble(MontoPagadoTXT.getText())) + " Bs");
            // Inicializar TotalBS y TotalUSD si es necesario
            if (TotalBS == 0.0 || TotalUSD == 0.0) {
                TotalBS = totalBs.doubleValue();
                TotalUSD = totalUSD.doubleValue();
            }

            // Validar que el monto pagado no exceda el total en dólares
            if (montoPagado.compareTo(totalUSD) > 0) {
                mostrarAlerta("Error", "Monto excedido", "El monto pagado no puede ser mayor que el total pendiente en dólares.", Alert.AlertType.ERROR);
                return; // Detener la ejecución si el monto es mayor
            }

            // Verificar si el monto pagado es igual al total en dólares
            System.out.println(TotalUSDTXT.getText().replace(" ", "").replace("$", ""));
            if (Double.parseDouble(TotalUSDTXT.getText().replace(" ", "").replace("$", "")) == 0.00) {
                MetodosPago.add(ComboMetodoPago.getValue());
                MetodosPago.add(montoPagado.toString());
                MetodosPago.add(NroReferenciaTXT.getText());

                // Convertir el monto pagado a bolívares y restarlo del total en bolívares
                BigDecimal montoPagadoBs = montoPagado.multiply(new BigDecimal(getCurrency.getCurrency().bcv())).setScale(2, RoundingMode.HALF_UP);
                totalBs = totalBs.subtract(montoPagadoBs).setScale(2, RoundingMode.HALF_UP);
                Totales.set(1, totalBs.doubleValue());

                // Restar el monto pagado del total en dólares
                totalUSD = totalUSD.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
                Totales.set(0, totalUSD.doubleValue());

                // Actualizar las etiquetas
                TotalUSDTXT.setText(totalUSD.toString() + " $");
                TotalBsTXT.setText(totalBs.toString() + " Bs");

                // Verificar si el pago fue exitoso
                if (PagarFactura()) {
                    mostrarAlerta("Pago Exitoso", "El pago se ha realizado con éxito", "El monto de pago es igual al total de la factura", Alert.AlertType.INFORMATION);
                    ((Stage) TotalBsTXT.getScene().getWindow()).close();
                    dashboardFacturarModel.setCleanALL(true);
                } else {
                    mostrarAlerta("Error", "No se ha podido realizar el pago", "Ha ocurrido un error al realizar el pago", Alert.AlertType.ERROR);
                }
            } else {
                // Si el monto pagado no es igual al total en dólares
                MetodosPago.add(ComboMetodoPago.getValue());
                MetodosPago.add(montoPagado.toString());
                MetodosPago.add(NroReferenciaTXT.getText());

                // Convertir el monto pagado a bolívares y restarlo del total en bolívares
                BigDecimal montoPagadoBs = montoPagado.multiply(BigDecimal.valueOf(getCurrency.getCurrency().bcv())).setScale(2, RoundingMode.HALF_UP);
                totalBs = totalBs.subtract(montoPagadoBs).setScale(2, RoundingMode.HALF_UP);
                Totales.set(1, totalBs.doubleValue());

                // Restar el monto pagado del total en dólares
                totalUSD = totalUSD.subtract(montoPagado).setScale(2, RoundingMode.HALF_UP);
                Totales.set(0, totalUSD.doubleValue());

                // Actualizar las etiquetas
                TotalUSDTXT.setText(totalUSD.toString() + " $");
                TotalBsTXT.setText(totalBs.toString() + " Bs");

                // Mostrar alerta de pago pendiente
                mostrarAlerta("Pago Pendiente", "Pendiente por Pagar", "Pendiente por Pagar: " + totalBs.toString() + " bs", Alert.AlertType.CONFIRMATION);
            }
        } else {
            // Si no se selecciona un método de pago válido
            mostrarAlerta("Error", "Campos Vacíos", "Seleccione un método de pago", Alert.AlertType.ERROR);
        }
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

    private void CleanFields() {
        ComboMetodoPago.setValue(null);
        MontoPagadoTXT.setText("");
        NroReferenciaTXT.setText("");
        TotalBsTXT.setText("");
        TotalUSDTXT.setText("");
    }

    public boolean PagarFactura() {
        System.out.println(TotalBS);
        System.out.println(TotalUSD);
        System.out.println(dashboardFacturarModel.GetProducto());
        System.out.println(MetodosPago);
        System.out.println(LocalDate.now().toString());
        System.out.println(dashboardFacturarModel.getIDCliente());
        return Pagarmodel.insertarFacturaCompleta(dashboardFacturarModel.getIDCliente(), LocalDate.now().toString(), TotalBS,TotalUSD ,"Pagado", Pagarmodel.setDetallesFactura(dashboardFacturarModel.GetProducto()), Pagarmodel.setMetodosPago(MetodosPago));
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
        ComboMetodoPago.getItems().addAll("Efectivo Bolivares", "Efectivo USD", "Pago Movil", "Transferencia Bancaria", "Zinli", "Binance","Biopago","Punto de Venta");
        Visible(false);
        Totales = dashboardFacturarModel.ObtenerTotales();
        TotalUSDTXT.setText(Totales.get(0).toString() + " $");
        TotalBsTXT.setText(Totales.get(1).toString() + " Bs");
    }
}
