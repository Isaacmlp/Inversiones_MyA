package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvView extends JFrame {
    public  JTextField IDProductotxt;
    public JTextField NombreProductoTxT;
    public JLabel IDProductoLabel;
    public JLabel TituloLabel;
    public JLabel NombreProductoLabel;
    public JTextField CantidadTXT;
    public JComboBox comboBoxUSD;
    public JPanel GuardarProductosPanel;
    public JLabel UnidadesLabel;
    public JLabel precioDeCostUSDLabel;

    public JPanel ProductPanel;
    public JTextField CostoProductoUSDTXT;
    public JTextField PrecioVentaUSDTXT;
    public JTextField DescripcionTXT;
    public JLabel DescripcionLabel;
    public JButton guardarButton;
    public JButton buscarButton;

    private void InitInv() {
        setTitle("Inversiones MyA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public InvView() {
        setContentPane(ProductPanel);
        InitInv();

    }

    public void Alert(String Message) {
        JOptionPane.showMessageDialog(this,Message);
    }

    public JTextField getIDProductotxt() {
        return IDProductotxt;
    }

    public void setIDProductotxt(JTextField IDProductotxt) {
        this.IDProductotxt = IDProductotxt;
    }

    public JTextField getNombreProductoTxT() {
        return NombreProductoTxT;
    }

    public void setNombreProductoTxT(JTextField nombreProductoTxT) {
        NombreProductoTxT = nombreProductoTxT;
    }

    public JLabel getTituloLabel() {
        return TituloLabel;
    }

    public void setTituloLabel(JLabel tituloLabel) {
        TituloLabel = tituloLabel;
    }

    public JLabel getIDProductoLabel() {
        return IDProductoLabel;
    }

    public void setIDProductoLabel(JLabel IDProductoLabel) {
        this.IDProductoLabel = IDProductoLabel;
    }

    public JLabel getNombreProductoLabel() {
        return NombreProductoLabel;
    }

    public void setNombreProductoLabel(JLabel nombreProductoLabel) {
        NombreProductoLabel = nombreProductoLabel;
    }

    public JTextField getCantidadTXT() {
        return CantidadTXT;
    }

    public void setCantidadTXT(JTextField cantidadTXT) {
        CantidadTXT = cantidadTXT;
    }

    public JComboBox getComboBoxUSD() {
        return comboBoxUSD;
    }

    public void setComboBoxUSD(JComboBox comboBoxUSD) {
        this.comboBoxUSD = comboBoxUSD;
    }

    public JLabel getUnidadesLabel() {
        return UnidadesLabel;
    }

    public void setUnidadesLabel(JLabel unidadesLabel) {
        UnidadesLabel = unidadesLabel;
    }

    public JLabel getPrecioDeCostUSDLabel() {
        return precioDeCostUSDLabel;
    }

    public void setPrecioDeCostUSDLabel(JLabel precioDeCostUSDLabel) {
        this.precioDeCostUSDLabel = precioDeCostUSDLabel;
    }

    public JTextField getCostoProductoUSDTXT() {
        return CostoProductoUSDTXT;
    }

    public void setCostoProductoUSDTXT(JTextField costoProductoUSDTXT) {
        this.CostoProductoUSDTXT = costoProductoUSDTXT;
    }

    public JTextField getPrecioVentaUSDTXT() {
        return PrecioVentaUSDTXT;
    }

    public void setPrecioVentaUSDTXT(JTextField precioVentaUSDTXT) {
        this.PrecioVentaUSDTXT = precioVentaUSDTXT;
    }

    public JTextField getDescripcionTXT() {
        return DescripcionTXT;
    }

    public void setDescripcionTXT(JTextField descripcionTXT) {
        this.DescripcionTXT = descripcionTXT;
    }

    public JLabel getDescripcionLabel() {
        return DescripcionLabel;
    }

    public void setDescripcionLabel(JLabel descripcionLabel) {
        DescripcionLabel = descripcionLabel;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
