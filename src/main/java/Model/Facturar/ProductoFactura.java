package Model.Facturar;

public class ProductoFactura {
    private String Nombre;
    private double Cantidad;
    private double PrecioUSD;
    private double PrecioBS;
    private double TotalBs;
    private double TotalUSD;

    public ProductoFactura(String Nombre, double Cantidad, double PrecioUSD, double PrecioBS, double TotalBs, double TotalUSD) {
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.PrecioUSD = PrecioUSD;
        this.PrecioBS = PrecioBS;
        this.TotalBs = TotalBs;
        this.TotalUSD = TotalUSD;
    }

    public ProductoFactura(String Nombre, double Cantidad, double PrecioUSD, double PrecioBS) {
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.PrecioUSD = PrecioUSD;
        this.PrecioBS = PrecioBS;
        this.TotalBs += PrecioBS;
        this.TotalUSD += PrecioUSD;

    }

    public String getNombreFacturado() {
        return Nombre;
    }

    public void setNombreFacturado(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getCantidadFacturado() {
        return Cantidad;
    }

    public void setCantidadFacturado(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioUSD() {
        return PrecioUSD;
    }

    public void setPrecioUSD(double PrecioUSD) {
        this.PrecioUSD = PrecioUSD;
    }

    public double getPrecioBS() {
        return PrecioBS;
    }

    public void setPrecioBS(double PrecioBS) {
        this.PrecioBS = PrecioBS;
    }

    public double getTotalBs() {
        return TotalBs;
    }

    public void setTotalBs(double TotalBs) {
        this.TotalBs = TotalBs;
    }

    public double getTotalUSD() {
        return TotalUSD;
    }

    public void setTotalUSD(double TotalUSD) {
        this.TotalUSD = TotalUSD;
    }

}
