/*
 * Created by JFormDesigner on Fri Nov 22 19:35:01 GMT+01:00 2024
 */

package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Isaac León
 */
public class InvView extends JFrame {
    public InvView() {
        initComponents();
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Isaac
        Panel_Inv = new JPanel();
        Productos = new JPanel();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== Panel_Inv ========
        {
            Panel_Inv.setFont(Panel_Inv.getFont().deriveFont(Panel_Inv.getFont().getSize() + 18f));
            Panel_Inv.setPreferredSize(new Dimension(1300, 130));
            Panel_Inv.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,Panel_Inv. getBorder () ) ); Panel_Inv. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            Panel_Inv.setLayout(null);

            //======== Productos ========
            {
                Productos.setToolTipText("Productos");
                Productos.setBorder(new TitledBorder("Productos"));
                Productos.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < Productos.getComponentCount(); i++) {
                        Rectangle bounds = Productos.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Productos.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Productos.setMinimumSize(preferredSize);
                    Productos.setPreferredSize(preferredSize);
                }
            }
            Panel_Inv.add(Productos);
            Productos.setBounds(665, 125, 540, 475);

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder("Ingresar Producto"));
                panel1.setLayout(null);

                //---- label2 ----
                label2.setText("ID");
                panel1.add(label2);
                label2.setBounds(30, 25, 35, 30);

                //---- textField1 ----
                textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                panel1.add(textField1);
                textField1.setBounds(75, 30, 80, 25);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            Panel_Inv.add(panel1);
            panel1.setBounds(195, 125, 330, 485);

            //---- label1 ----
            label1.setText("INVENTARIO");
            label1.setMinimumSize(new Dimension(100, 100));
            label1.setMaximumSize(new Dimension(200, 200));
            label1.setFont(new Font("Inter", Font.BOLD, 50));
            Panel_Inv.add(label1);
            label1.setBounds(435, 25, 340, 45);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < Panel_Inv.getComponentCount(); i++) {
                    Rectangle bounds = Panel_Inv.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = Panel_Inv.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                Panel_Inv.setMinimumSize(preferredSize);
                Panel_Inv.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Isaac
    private JPanel Panel_Inv;
    private JPanel Productos;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
