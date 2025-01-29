package Model.Facturas;

import Utils.GetCurrency;

import java.util.List;

public class FacturaGenerada {
    GetCurrency getCurrency = new GetCurrency();

    public void setTotalPagadoBs(double totalPagadoBs) {
        TotalPagadoBs = totalPagadoBs;
    }

    public void setTotalPagadoUSD(double totalPagadoUSD) {
        TotalPagadoUSD = totalPagadoUSD;
    }

    /* Factura*/
    private double TotalPagadoBs;



    private double TotalPagadoUSD;
   private int numeroFactura;
   private String FechaFactura;
   private double totalBsFactura;
   private double totalUSDFactura;
   private String estadoFactura;

        /* Cliente*/
   private String NombreCliente;
   private String ApellidoCliente;
   private String TelefonoCliente;
   private String DireccionCliente;
   private String CorreoCliente;
   private String CedulaCliente;

   /* Producto*/
    private int DetalleFactura;
    private List<Producto> Producto;

        /* Pago*/
    private List<MetodoPago> Pago;



    public FacturaGenerada(List<MetodoPago> pago, List<Model.Facturas.Producto> producto, int detalleFactura, String cedulaCliente, String correoCliente, String direccionCliente, String telefonoCliente, String apellidoCliente, String nombreCliente, String estadoFactura, double totalUSDFactura, double totalBsFactura, String fechaFactura, int numeroFactura, double totalPagadoUSD, double totalPagadoBs) {
        this.Pago = pago;
        this.Producto = producto;
        this.DetalleFactura = detalleFactura;
        this.CedulaCliente = cedulaCliente;
        this.CorreoCliente = correoCliente;
        this.DireccionCliente = direccionCliente;
        this.TelefonoCliente = telefonoCliente;
        this.ApellidoCliente = apellidoCliente;
        this.NombreCliente = nombreCliente;
        this.estadoFactura = estadoFactura;
        this.totalUSDFactura = totalUSDFactura;
        this.totalBsFactura = totalBsFactura;
        this.FechaFactura = fechaFactura;
        this.numeroFactura = numeroFactura;
        this.TotalPagadoUSD = totalPagadoUSD;
        this.TotalPagadoBs = totalPagadoBs;
    }

    public FacturaGenerada() {

    }

    public double getTotalPagadoBs() {
        return TotalPagadoBs;
    }

    public double getTotalPagadoUSD() {
        return TotalPagadoUSD;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFechaFactura() {
        return FechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        FechaFactura = fechaFactura;
    }

    public double getTotalBsFactura() {
        return totalBsFactura;
    }

    public void setTotalBsFactura(double totalBsFactura) {
        this.totalBsFactura = totalBsFactura;
    }

    public double getTotalUSDFactura() {
        return totalUSDFactura;
    }

    public void setTotalUSDFactura(double totalUSDFactura) {
        this.totalUSDFactura = totalUSDFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return ApellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        ApellidoCliente = apellidoCliente;
    }

    public String getTelefonoCliente() {
        return TelefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        TelefonoCliente = telefonoCliente;
    }

    public String getDireccionCliente() {
        return DireccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        DireccionCliente = direccionCliente;
    }

    public String getCorreoCliente() {
        return CorreoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        CorreoCliente = correoCliente;
    }

    public String getCedulaCliente() {
        return CedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        CedulaCliente = cedulaCliente;
    }

    public int getDetalleFactura() {
        return DetalleFactura;
    }

    public void setDetalleFactura(int detalleFactura) {
        DetalleFactura = detalleFactura;
    }

    public List<Producto> getProducto() {
        return Producto;
    }

    public void setProducto(List<Producto> producto) {
        Producto = producto;
    }

    public List<MetodoPago> getPago() {
        return Pago;
    }

    public void setPago(List<MetodoPago> pago) {
        Pago = pago;
    }



}
