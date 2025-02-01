package Model.Inventario;

import java.util.Date;
import java.util.List;

public class Inventario {
    public List<Item> productos;
    public double valorTotal;
    public double totalProductos;
    public Date fecha;

    public Inventario(List<Item> productos, double valorTotal, double totalProductos, Date fecha) {
        this.productos = productos;
        this.valorTotal = valorTotal;
        this.totalProductos = totalProductos;
        this.fecha = fecha;
    }

    public List<Item> getProductos() {
        return productos;
    }

    public void setProductos(List<Item> productos) {
        this.productos = productos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(double totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
