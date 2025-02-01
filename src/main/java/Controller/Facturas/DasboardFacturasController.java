package Controller.Facturas;

import Model.Facturas.Factura;
import Model.Facturas.FacturasModel;
import Model.User;
import Utils.OpenView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class DasboardFacturasController {
    FacturasModel Facturas = new FacturasModel();
    OpenView Open = new OpenView();

    @FXML
    private TableColumn<Factura, String> CedulaClienteColumn;

    @FXML
    private TableColumn<Factura, String> EstadoFacturaColumn;

    @FXML
    private TableColumn<Factura, String> FechaFacturaColumn;

    @FXML
    private DatePicker FechaFacturaPicker;

    @FXML
    private TableColumn<Factura, String> NumeroFacturaColumn;

    @FXML
    private TableColumn<Factura, Double> TotalBsFacturaColumn;

    @FXML
    private TableColumn<Factura, Double> TotalUSDFacturaColumn;

    @FXML
    private TableView<Factura> TablaProductos;

    @FXML
    private Label BinanceLBL;

    @FXML
    private Label BinanceTXT;

    @FXML
    private Label BiopagoLBL;

    @FXML
    private Label BiopagoTXT;

    @FXML
    private Label EfectivoBSLBL;

    @FXML
    private Label EfectivoUSDLBL;

    @FXML
    private Label EfectivoUSDTXT;

    @FXML
    private Label EfectivobsTXT;


    @FXML
    private Label PagoMovilLBL;

    @FXML
    private Label PagomovilTXT;

    @FXML
    private Label PuntodeVentaLBL;

    @FXML
    private Label PuntodeVentaTXT;

    @FXML
    private Label TotalBSLBL;

    @FXML
    private Label TotalBsTXT;

    @FXML
    private TitledPane TotalTitle;

    @FXML
    private Label TotalUSDLBL;

    @FXML
    private Label TotalUSDTXT;

    @FXML
    private Label TransferenciaLBL;

    @FXML
    private Label TransferenciaTXT;

    @FXML
    private Label ZinliLBL;

    @FXML
    private Label ZinliTXT;

    @FXML
    void BuscarAlSeleccionarFecha(ActionEvent event) {
        if (FechaFacturaPicker.getValue() != null) {
            IsVisible(true);
            CargarMetodosPago();
            Facturas.CargarFacturasTabla(NumeroFacturaColumn, CedulaClienteColumn, EstadoFacturaColumn, FechaFacturaColumn, TotalBsFacturaColumn, TotalUSDFacturaColumn,TablaProductos,FechaFacturaPicker.getValue().toString().formatted("yyyy-MM-dd"));
        } else {
            IsVisible(false);
            CleanFields();
            return;
        }
    }

    private void CleanFields() {
        EfectivobsTXT.setText("");
        EfectivoUSDTXT.setText("");
        PagomovilTXT.setText("");
        TransferenciaTXT.setText("");
        ZinliTXT.setText("");
        BinanceTXT.setText("");
        BiopagoTXT.setText("");
        PuntodeVentaTXT.setText("");
    }

    private void CargarMetodosPago() {
        ArrayList<String> metodosPago = Facturas.getMetodosPago(FechaFacturaPicker.getValue().toString().formatted("yyyy-MM-dd"));
        TotalBsTXT.setText((Double.parseDouble(metodosPago.get(0)) + Double.parseDouble(metodosPago.get(2)) + Double.parseDouble(metodosPago.get(3)) + Double.parseDouble(metodosPago.get(6))+ Double.parseDouble(metodosPago.get(7))) + " Bs");
        TotalUSDTXT.setText((Double.parseDouble(metodosPago.get(1)) + Double.parseDouble(metodosPago.get(4)) + Double.parseDouble(metodosPago.get(5))) + " $");
        EfectivobsTXT.setText(metodosPago.get(0));
        EfectivoUSDTXT.setText(metodosPago.get(1));
        PagomovilTXT.setText(metodosPago.get(2));
        TransferenciaTXT.setText(metodosPago.get(3));
        ZinliTXT.setText(metodosPago.get(4));
        BinanceTXT.setText(metodosPago.get(5));
        BiopagoTXT.setText(metodosPago.get(6));
        PuntodeVentaTXT.setText(metodosPago.get(7));
    }

    @FXML
    void VerFactura(ActionEvent event) throws Exception {
        ArrayList<String> facturas = Facturas.getFacturaSeleccionada(TablaProductos);
        if (facturas != null) {
            Facturas.setIdFactura(Integer.parseInt(facturas.getFirst()));
            Open.VerFacturas(Facturas);
        }
    }

    private void IsVisible(boolean visible) {
        TotalBSLBL.setVisible(visible);
        TotalBsTXT.setVisible(visible);
        TotalUSDLBL.setVisible(visible);
        TotalUSDTXT.setVisible(visible);
        BinanceLBL.setVisible(visible);
        BinanceTXT.setVisible(visible);
        BiopagoLBL.setVisible(visible);
        BiopagoTXT.setVisible(visible);
        EfectivoBSLBL.setVisible(visible);
        EfectivoUSDLBL.setVisible(visible);
        EfectivoUSDTXT.setVisible(visible);
        EfectivobsTXT.setVisible(visible);
        PagoMovilLBL.setVisible(visible);
        PagomovilTXT.setVisible(visible);
        PuntodeVentaLBL.setVisible(visible);
        PuntodeVentaTXT.setVisible(visible);
        TransferenciaLBL.setVisible(visible);
        TransferenciaTXT.setVisible(visible);
        ZinliLBL.setVisible(visible);
        ZinliTXT.setVisible(visible);
        TotalTitle.setVisible(visible);
    }

    @FXML
    public void initialize(User user) {
        IsVisible(false);
        //Facturas.CargarFacturasTabla(NumeroFacturaColumn, CedulaClienteColumn, EstadoFacturaColumn, FechaFacturaColumn, TotalBsFacturaColumn, TotalUSDFacturaColumn,TablaProductos);
    }

}
