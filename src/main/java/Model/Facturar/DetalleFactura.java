package Model.Facturar;

class DetalleFactura {
    private String nombreProducto;
    private Double cantidad;
    private double precioUnitariobs;
    private double precioUnitarioUSD;

    public DetalleFactura(String nombreProducto, Double Cantidad, double precioUnitariobs, double precioUnitarioUSD) {
        this.nombreProducto = nombreProducto;
        this.cantidad = Cantidad;
        this.precioUnitariobs = precioUnitariobs;
        this.precioUnitarioUSD = precioUnitarioUSD;
    }

    public double getPrecioUnitariobs() {
        return precioUnitariobs;
    }

    public double getPrecioUnitarioUSD() {
        return precioUnitarioUSD;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Double getCantidadFactura() {
        return cantidad;
    }

}