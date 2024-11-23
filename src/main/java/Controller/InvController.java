package Controller;
import View.InvView;
import Model.InvModel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class InvController {
    InvView Inv;
    InvModel invModel;


    public InvController(InvView invView, InvModel invModel) {
        this.Inv = invView;
        this.invModel = invModel;
        ActionListener();
        ItemListener();
    }

    public void ActionListener() {
        Inv.getGuardarButton().addActionListener(e -> Inv.Alert(invModel.GuardarProducto(Inv.getNombreProductoTxT().getText(),Inv.getDescripcionTXT().getText(),Inv.getCantidadTXT().getText(),Inv.getCostoProductoUSDTXT().getText(),Inv.getPrecioVentaUSDTXT().getText())));

        Inv.getBuscarButton().addActionListener(e -> Inv.Alert("Btn Buscar funcionando"));
    }

    public void ItemListener() {
        Inv.getComboBoxUSD().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();

                switch (selectedItem) {
                    case "5%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.05);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "10%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.1);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "15%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.15);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "20%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.2);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "25%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.25);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "30%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.3);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "35%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.35);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "40%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.4);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "45%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.45);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                    case "50%" -> {
                        double Auxiliar = (parseDouble(Inv.getCostoProductoUSDTXT().getText()) * 1.5);
                        Inv.getPrecioVentaUSDTXT().setText(String.format(Locale.US,"%.2f", Auxiliar));
                    }
                }
            }
        });
    }
}




