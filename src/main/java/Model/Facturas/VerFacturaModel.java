package Model.Facturas;

import Utils.ConectBD;
import Utils.GetCurrency;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;

public class VerFacturaModel {
    ConectBD Conect = new ConectBD();
    GetCurrency GetCurrency = new GetCurrency();

    public FacturaGenerada VerFactura(int idFactura) {
        String sql = """
                DECLARE @TotalFacturaBs FLOAT;
                EXEC Ventas.ObtenerFacturaPorID @ID_Factura = ?, @TotalFacturaBs = @TotalFacturaBs OUTPUT;
                SELECT @TotalFacturaBs AS TotalFacturaBs;""";

        FacturaGenerada factura = new FacturaGenerada();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<MetodoPago> metodosPago = new ArrayList<>();

        try (Connection conn = Conect.Conect();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Configurar el parámetro de entrada (ID de la factura)
            stmt.setInt(1, idFactura);

            // Ejecutar el procedimiento almacenado
            boolean tieneResultados = stmt.execute();

            // Procesar los resultados
            if (tieneResultados) {
                // Primer conjunto de resultados: Información de la factura y el cliente
                try (ResultSet rs1 = stmt.getResultSet()) {
                    if (rs1 != null && rs1.next()) {
                        factura.setNumeroFactura(rs1.getInt("FacturaID"));
                        factura.setFechaFactura(String.valueOf(rs1.getDate("FechaFactura")));
                        factura.setTotalBsFactura(rs1.getDouble("TotalBs"));
                        factura.setTotalUSDFactura(rs1.getDouble("TotalUSD"));
                        factura.setEstadoFactura(rs1.getString("EstadoFactura"));
                        factura.setNombreCliente((rs1.getString("NombreCliente")) + " " + (rs1.getString("ApellidoCliente")));
                        factura.setCedulaCliente(rs1.getString("CedulaCliente"));
                        factura.setTelefonoCliente(rs1.getString("TelefonoCliente"));
                        factura.setDireccionCliente(rs1.getString("DireccionCliente"));
                        factura.setCorreoCliente(rs1.getString("CorreoCliente"));
                    }
                }

                // Segundo conjunto de resultados: Detalles de la factura (productos)
                if (stmt.getMoreResults()) {
                    try (ResultSet rs2 = stmt.getResultSet()) {
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Producto producto = new Producto();
                                producto.setDetalleFacturaID(rs2.getInt("DetalleFacturaID"));
                                producto.setIDProducto(rs2.getInt("ProductoID"));
                                producto.setNombreProducto(rs2.getString("NombreProducto"));
                                producto.setCantidad(rs2.getInt("Cantidad"));
                                producto.setPrecioUnitarioBs(rs2.getDouble("PrecioUnitarioBs"));
                                producto.setPrecioUnitarioUSD(rs2.getDouble("PrecioUnitarioUSD"));
                                producto.setSubTotalBs(rs2.getDouble("SubTotalBs"));
                                producto.setSubTotalUSD(rs2.getDouble("SubTotalUSD"));
                                productos.add(producto);
                            }
                        }
                    }
                }

                // Tercer conjunto de resultados: Métodos de pago
                if (stmt.getMoreResults()) {
                    try (ResultSet rs3 = stmt.getResultSet()) {
                        if (rs3 != null) {
                            while (rs3.next()) {
                                MetodoPago metodoPago = new MetodoPago();
                                metodoPago.setIDMetodoPago(rs3.getInt("PagoID"));
                                metodoPago.setMetodoPago(rs3.getString("MetodoPago"));
                                metodoPago.setMontoPagado(rs3.getDouble("MontoPago"));
                                metodosPago.add(metodoPago);
                            }

                        }
                    }
                }

                if (stmt.getMoreResults()) {
                    try (ResultSet rs4 = stmt.getResultSet()) {
                        if (rs4 != null) {
                            if (rs4.next()) {
                                factura.setTotalBsFactura(rs4.getDouble("TotalFacturaBs"));
                                double USD = rs4.getDouble("TotalFacturaBs") / GetCurrency.getCurrency().bcv();
                                BigDecimal TotalFacturaUSD = new BigDecimal(USD).setScale(2, RoundingMode.HALF_UP);
                                factura.setTotalUSDFactura(TotalFacturaUSD.doubleValue());
                            }
                        }
                    }
                }
                // Asignar productos y métodos de pago a la factura
                factura.setProducto(productos);
                factura.setPago(metodosPago);

                return factura;
            } else {
                System.out.println("No se encontraron resultados para la factura.");
                return null;
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Error al obtener la factura: " + e.getMessage());
            alert.showAndWait();
        }
        return null;
    }
}