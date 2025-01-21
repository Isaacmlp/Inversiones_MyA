package Model;

public class Producto {
    private String Nombre;
    private String Descripcion;
    private String Cantidad;
    private String PrecioDeCostoUSD;
    private String PrecioDeCostoBS;
    private String PrecioVentaUSD;
    private String PrecioVentaBS;

    public Producto(String Nombre, String Descripcion, String Cantidad, String PrecioDeCostoUSD,String PrecioDeCostoBS,String PrecioVentaUSD, String PrecioVentaBS) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.PrecioDeCostoUSD = PrecioDeCostoUSD;
        this.PrecioDeCostoBS = PrecioDeCostoBS;
        this.PrecioVentaUSD = PrecioVentaUSD;
        this.PrecioVentaBS = PrecioVentaBS;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPrecioDeCostoUSD() {
        return PrecioDeCostoUSD;
    }

    public void setPrecioDeCostoUSD(String precioDeCostoUSD) {
        PrecioDeCostoUSD = precioDeCostoUSD;
    }

    public String getPrecioDeCostoBS() {
        return PrecioDeCostoBS;
    }

    public void setPrecioDeCostoBS(String precioDeCostoBS) {
        PrecioDeCostoBS = precioDeCostoBS;
    }

    public String getPrecioVentaUSD() {
        return PrecioVentaUSD;
    }

    public void setPrecioVentaUSD(String precioVentaUSD) {
        PrecioVentaUSD = precioVentaUSD;
    }

    public String getPrecioVentaBS() {
        return PrecioVentaBS;
    }

    public void setPrecioVentaBS(String precioVentaBS) {
        PrecioVentaBS = precioVentaBS;
    }
}
