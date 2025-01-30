package Controller.Facturas;

import Model.Facturas.FacturaGenerada;
import Model.Facturas.FacturasModel;
import Model.Facturas.VerFacturaModel;
import Model.FileChoose;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static Model.GenerateHTML.generarHTML;
import static Model.PDF.generarPDF;

public class VerFacturaCotroller {
    VerFacturaModel VerFactura = new VerFacturaModel();
    FileChoose FileChoose = new FileChoose();
    FacturasModel FacturaModel = new FacturasModel();
    FacturaGenerada factura;

    int idFactura;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @FXML
    private Button btn;

    @FXML
    void GenerarPDF(ActionEvent event) {
        FacturaGenerada factura = VerFactura.VerFactura(FacturaModel.getIdFactura());
        if (factura == null) {
            return;
        }
        factura = VerFactura.VerFactura(idFactura);

        String html = generarHTML(factura);
        generarPDF(html, FileChoose.EscojerRuta(btn, String.valueOf(factura.getNumeroFactura())));
        ((Stage) btn.getScene().getWindow()).close();

    }


    @FXML
    public void initialize(FacturasModel facturas) {
        this.FacturaModel = facturas;
    }
}
