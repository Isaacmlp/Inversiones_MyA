package Model.Facturar;

class DetalleFactura {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitariobs;
    private double precioUnitarioUSD;

    public DetalleFactura(String nombreProducto, int Cantidad, double precioUnitariobs, double precioUnitarioUSD) {
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

    public int getCantidadFactura() {
        return cantidad;
    }

}