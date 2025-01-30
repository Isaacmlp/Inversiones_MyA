package Model.Inventario;

public class ReporteInventario {
    int ID;
    String Nombre;
    double Cantidad;
    double PrecioUnitarioBs;
    double PrecioUnitarioUSD;
    double ValorTotal;

    public ReporteInventario(int ID, String Nombre, double Cantidad, double PrecioUnitarioBs, double PrecioUnitarioUSD, double ValorTotal) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.PrecioUnitarioBs = PrecioUnitarioBs;
        this.PrecioUnitarioUSD = PrecioUnitarioUSD;
        this.ValorTotal = ValorTotal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
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

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }
}
