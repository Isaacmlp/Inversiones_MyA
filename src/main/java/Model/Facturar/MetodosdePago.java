package Model.Facturar;

public class MetodosdePago {
    private String nombreMetodosPago;
    private double MontoPagado;

    public MetodosdePago( String nombreMetodosPago, double MontoPagado) {
        this.nombreMetodosPago = nombreMetodosPago;
        this.MontoPagado = MontoPagado;
    }

    public String getNombreMetodosPago() {
        return nombreMetodosPago;
    }

    public double getMontoPagado() {
        return MontoPagado;
    }
}
