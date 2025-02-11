package Model.Facturas;

import Utils.ConectBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;


public class FacturasModel {
    ConectBD Conect = new ConectBD();
    private int idFactura;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int IDFactura) {
        this.idFactura = IDFactura;
    }

    public ArrayList<Factura> getFacturas() {
            String sql = "SELECT * FROM Ventas.Factura";
            ArrayList<Factura> facturas =  new ArrayList<>();
            try (Connection con = Conect.Conect();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    facturas.add(new Factura(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(6),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getDouble(5)
                    ));
                }
            } catch (SQLException ex) {
                System.out.println("Error al obtener las facturas" + ex.getMessage());
                ex.printStackTrace();
                throw new RuntimeException("Error al obtener las facturas", ex);
            }
            return facturas;
        }

    public String getCedulaCliente(int idCliente) {
        String sql = "SELECT CEDULA FROM Clientes.Cliente WHERE ID = ?";
        try (Connection con = Conect.Conect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("CEDULA");
            } else {
                throw new RuntimeException("No se encontró la cédula del cliente con ID: " + idCliente);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cédula del cliente: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener la cédula del cliente", ex);
        }
    }

    public ArrayList<Double> getTotales(String fecha) {
        String sql = "SELECT SUM(TotalBS) AS TotalBS, SUM(TotalUSD) AS TotalUSD FROM Ventas.Factura WHERE FECHA = ?";
        ArrayList<Double> totales = new ArrayList<>();
        try (Connection con = Conect.Conect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totales.add(rs.getDouble("TotalBS"));
                totales.add(rs.getDouble("TotalUSD"));
            }
            return totales;
        } catch (SQLException ex) {
            System.out.println("Error al obtener las facturas" + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener las facturas", ex);
        }
    }

    public ArrayList<Factura> getFacturas(String fecha) {
            String sql = "SELECT * FROM Ventas.Factura WHERE FECHA = ?";
        ArrayList<Model.Facturas.Factura> facturas =  new ArrayList<>();
        try (Connection con = Conect.Conect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fecha);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                facturas.add(new Model.Facturas.Factura(
                        rs.getInt(1),
                        getCedulaCliente(rs.getInt(2)),
                        rs.getString(6),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5)
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener las facturas" + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Error al obtener las facturas", ex);
        }
        return facturas;
    }

    public void CargarFacturasTabla(TableColumn<Factura, String> NumeroFacturaColumn, TableColumn<Factura, String> CedulaClienteColumn, TableColumn<Factura, String> EstadoFacturaColumn, TableColumn<Factura, String> FechaFacturaColumn, TableColumn<Factura, Double> TotalBsFacturaColumn, TableColumn<Factura, Double> TotalUSDFacturaColumn, TableView<Factura> TablaFacturas,String fecha) {
        NumeroFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        CedulaClienteColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
        EstadoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("estadoFactura"));
        FechaFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFactura"));
        TotalBsFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("totalBsFactura"));
        TotalUSDFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("totalUSDFactura"));

        CargarFacturas(TablaFacturas,fecha);
    }

    public void CargarFacturasTabla(TableColumn<Factura, String> NumeroFacturaColumn, TableColumn<Factura, String> CedulaClienteColumn, TableColumn<Factura, String> EstadoFacturaColumn, TableColumn<Factura, String> FechaFacturaColumn, TableColumn<Factura, Double> TotalBsFacturaColumn, TableColumn<Factura, Double> TotalUSDFacturaColumn, TableView<Factura> TablaFacturas) {
        NumeroFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        CedulaClienteColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
        EstadoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("estadoFactura"));
        FechaFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFactura"));
        TotalBsFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("totalBsFactura"));
        TotalUSDFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("totalUSDFactura"));

        CargarFacturas(TablaFacturas);
    }

    public void CargarFacturas (TableView<Factura> TablaFacturas) {
        ArrayList<Factura> Factur = getFacturas();
        ObservableList<Factura> Facturs = FXCollections.observableArrayList();
        for (Factura value : Factur) {
            Factura factura = new Factura(value.getNumeroFactura(),
                    value.getCedulaCliente(),
                    value.getEstadoFactura(),
                    value.getFechaFactura(),
                    value.getTotalBsFactura(),
                    value.getTotalUSDFactura()
            );
            Facturs.add(factura);
        }
        TablaFacturas.setItems(Facturs);
    }

    public void CargarFacturas (TableView<Factura> TablaFacturas,String fecha) {
        ArrayList<Factura> Factur = getFacturas(fecha);
        ObservableList<Factura> Facturs = FXCollections.observableArrayList();
        for (Factura value : Factur) {
            Factura factura = new Factura(value.getNumeroFactura(),
                    value.getCedulaCliente(),
                    value.getEstadoFactura(),
                    value.getFechaFactura(),
                    value.getTotalBsFactura(),
                    value.getTotalUSDFactura()
            );
            Facturs.add(factura);
        }
        TablaFacturas.setItems(Facturs);
    }

    public ArrayList<String> getMetodosPago(String fecha) {
        String sql = "{call Ventas.ObtenerMetodosPagoPorFecha(?)}";
        ArrayList<String> metodosPago = new ArrayList<>();
        try (Connection conn = Conect.Conect();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            // Configurar el parámetro de entrada (fecha)
            cstmt.setDate(1, Date.valueOf(fecha));

            // Ejecutar el procedimiento almacenado
            ResultSet rs = cstmt.executeQuery();

            // Procesar el ResultSet
            while (rs.next()) {
                 metodosPago.add(String.valueOf(rs.getDouble("TotalPagado")));
            }
            return metodosPago;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            // Manejar la excepción adecuadamente
        }
        return metodosPago;
    }

    public ArrayList<String> getFacturaSeleccionada(TableView<Factura> TablaFacturas) {
        Factura factura = TablaFacturas.getSelectionModel().getSelectedItem();
        ArrayList<String> facturas = new ArrayList<>();
        if (factura != null) {
            facturas.add(String.valueOf(factura.getNumeroFactura()));
            facturas.add(factura.getCedulaCliente());
            facturas.add(factura.getEstadoFactura());
            facturas.add(factura.getFechaFactura());
            facturas.add(String.valueOf(factura.getTotalBsFactura()));
            facturas.add(String.valueOf(factura.getTotalUSDFactura()));
            return facturas;
        }
        return null;
    }
}



