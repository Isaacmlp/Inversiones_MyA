package Model.Facturar;

import Utils.ConectBD;
import javafx.scene.control.Alert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PagarModel {
    ConectBD conectar = new ConectBD();

    private String convertListToXML(ArrayList<DetalleFactura> list) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<root>");

        for (DetalleFactura detalle : list) {
            xmlBuilder.append("<DetallesFactura>")
                    .append("<NombreProducto>").append(escapeXML(detalle.getNombreProducto())).append("</NombreProducto>")
                    .append("<Cantidad>").append(detalle.getCantidadFactura()).append("</Cantidad>")
                    .append("<PrecioUnitariobs>").append(detalle.getPrecioUnitariobs()).append("</PrecioUnitariobs>")
                    .append("<PrecioUnitarioUSD>").append(detalle.getPrecioUnitarioUSD()).append("</PrecioUnitarioUSD>");
            xmlBuilder.append("</DetallesFactura>");
        }
        xmlBuilder.append("</root>");

        System.out.println(xmlBuilder);
        return xmlBuilder.toString();
    }

    // Método para escapar caracteres especiales en XML
    private String escapeXML(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    public String setDetallesFactura(ArrayList<String> productos) {

        ArrayList<DetalleFactura> detallesFactura = new ArrayList<>();
        for (int i = 0; i < productos.size(); i += 5) {
            try {
                String nombreProducto = productos.get(i);
                Double cantidad = Double.parseDouble(productos.get(i + 4));
                double precioUnitario = Double.parseDouble(productos.get(i + 3));
                double descuento = Double.parseDouble(productos.get(i + 2));

                detallesFactura.add(new DetalleFactura(nombreProducto, cantidad, precioUnitario, descuento));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error al convertir los valores numéricos de los productos.", e);
            }
        }

        return convertListToXML(detallesFactura);
    }
    private String convertListToXMLs(ArrayList<MetodosdePago> list) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<root>");

        for (MetodosdePago detalle : list) {
            xmlBuilder.append("<MetodosPago>")
                    .append("<NombreMetodoPago>").append(escapeXMLPago(detalle.getNombreMetodosPago())).append("</NombreMetodoPago>")
                    .append("<Monto>").append(detalle.getMontoPagado()).append("</Monto>");
            xmlBuilder.append("</MetodosPago>");
        }
        xmlBuilder.append("</root>");
        System.out.println(xmlBuilder);

        return xmlBuilder.toString();
    }

    private String escapeXMLPago(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    public String setMetodosPago(ArrayList<String> metodosPago) {
        ArrayList<MetodosdePago> MetodosPago = new ArrayList<>();
        for (int i = 0; i < metodosPago.size(); i += 3) {
            MetodosPago.add(new MetodosdePago(metodosPago.get(i), Double.parseDouble(metodosPago.get(i + 1))));

        }
        return convertListToXMLs(MetodosPago);

    }

    public boolean insertarFacturaCompleta(int idCliente, String fecha, double totalbs, double totalusd, String estado,
                                           String detallesFactura, String metodosPago) {
        String sql = "{call Ventas.InsertarFacturaCompleta(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = conectar.Conect();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            // Configurar los parámetros de entrada
            cstmt.setInt(1, idCliente);
            cstmt.setString(2, fecha);
            cstmt.setDouble(3, totalbs);
            cstmt.setDouble(4, totalusd);
            cstmt.setString(5, estado);
            cstmt.setString(6, detallesFactura);
            cstmt.setString(7, metodosPago);

            // Registrar el parámetro de salida
            cstmt.registerOutParameter(8, Types.BIT);

            // Ejecutar el procedimiento almacenado
            cstmt.execute();

            // Obtener el valor del parámetro de salida

            // Retornar true solo si el procedimiento fue exitoso
            return cstmt.getBoolean(8);
        } catch (SQLException e) {
            // Mostrar el mensaje de error en una alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Error al insertar la factura: " + e.getMessage());
            alert.showAndWait();

            // Retornar false para indicar que la operación falló
            return false;
        }
    }
}
