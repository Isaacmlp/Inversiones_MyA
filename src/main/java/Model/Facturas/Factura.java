package Model.Facturas;

public class Factura {
    private int numeroFactura;
    private String cedulaCliente;
    private double totalBsFactura;
    private double totalUSDFactura;
    private String estadoFactura;
    private String fechaFactura;

    public Factura(int numeroFactura, String cedulaCliente, String estadoFactura, String fechaFactura, double totalBsFactura, double totalUSDFactura) {
        this.numeroFactura = numeroFactura;
        this.cedulaCliente = cedulaCliente;
        this.estadoFactura = estadoFactura;
        this.fechaFactura = fechaFactura;
        this.totalBsFactura = totalBsFactura;
        this.totalUSDFactura = totalUSDFactura;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public double getTotalBsFactura() {
        return totalBsFactura;
    }

    public double getTotalUSDFactura() {
        return totalUSDFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }
}
