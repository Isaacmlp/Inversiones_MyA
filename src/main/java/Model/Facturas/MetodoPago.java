package Model.Facturas;

public class MetodoPago {
    private int IDMetodoPago;
    private String MetodoPago;
    private double MontoPagado;

    public MetodoPago(int IDMetodoPago, String MetodoPago, double MontoPagado) {
        this.IDMetodoPago = IDMetodoPago;
        this.MetodoPago = MetodoPago;
        this.MontoPagado = MontoPagado;
    }

    public MetodoPago(){

    }

    public int getIDMetodoPago() {
        return IDMetodoPago;
    }

    public void setIDMetodoPago(int IDMetodoPago) {
        this.IDMetodoPago = IDMetodoPago;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
    }

    public double getMontoPagado() {
        return MontoPagado;
    }

    public void setMontoPagado(double MontoPagado) {
        this.MontoPagado = MontoPagado;
    }
}
