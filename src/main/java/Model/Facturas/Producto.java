package Model.Facturas;

public class Producto {
    private int IDProducto;
    private String NombreProducto;
    private double Cantidad;
    private double PrecioUnitarioBs;
    private double PrecioUnitarioUSD;
    private int DetalleFacturaID;
    private double SubTotalBs;
    private double SubTotalUSD;

    public Producto(int IDProducto, String NombreProducto, double Cantidad, double PrecioUnitarioBs, double PrecioUnitarioUSD, double SubtotalBs, double SubtotalUSD, int DetalleFacturaID, double SubTotalBs, double SubTotalUSD) {
        this.IDProducto = IDProducto;
        this.NombreProducto = NombreProducto;
        this.Cantidad = Cantidad;
        this.PrecioUnitarioBs = PrecioUnitarioBs;
        this.PrecioUnitarioUSD = PrecioUnitarioUSD;
        this.DetalleFacturaID = DetalleFacturaID;
        this.SubTotalBs = SubTotalBs;
        this.SubTotalUSD = SubTotalUSD;
    }

    public Producto() {

    }
    public int getDetalleFacturaID() {
        return DetalleFacturaID;
    }

    public double getSubTotalBs() {
        return SubTotalBs;
    }

    public void setSubTotalBs(double SubTotalBs) {
        this.SubTotalBs = SubTotalBs;
    }

    public double getSubTotalUSD() {
        return SubTotalUSD;
    }

    public void setSubTotalUSD(double SubTotalUSD) {
        this.SubTotalUSD = SubTotalUSD;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setDetalleFacturaID(int DetalleFacturaID) {
        this.DetalleFacturaID = DetalleFacturaID;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioUnitarioBs() {
        return PrecioUnitarioBs;
    }

    public void setPrecioUnitarioBs(double PrecioUnitarioBs) {
        this.PrecioUnitarioBs = PrecioUnitarioBs;
    }

    public double getPrecioUnitarioUSD() {
        return PrecioUnitarioUSD;
    }

    public void setPrecioUnitarioUSD(double PrecioUnitarioUSD) {
        this.PrecioUnitarioUSD = PrecioUnitarioUSD;
    }


}
