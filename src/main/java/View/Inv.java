package View;

import javax.swing.*;

public class Inv extends JFrame {
    private JTextField IDProductotxt;
    private JTextField NombreProductoTxT;
    private JTextArea DescripcionProductoArea;
    private JLabel IDProductoLabel;
    private JLabel TituloLabel;
    private JLabel NombreProductoLabel;
    private JLabel DescripcionProductoLabel;
    private JTextField CantidadTXT;
    private JComboBox comboBoxUSD;
    private JPanel DescripcionLabel;
    private JLabel catidadLabel;
    private JLabel precioDeCostUSDLabel;
    private JPanel ProductPanel;
    private JTable table1;

    private void InitInv() {
        setTitle("Inversiones MyA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public Inv() {
        setContentPane(ProductPanel);
        InitInv();
    }
}
