package Model.Inventario;

import java.time.LocalDate;
import java.util.List;

public class Inventario {
    public List<Item> productos;
    public double valorTotal;
    public double totalProductos;
    public LocalDate fecha;
    public String Usuario;

    public Inventario(List<Item> productos, double valorTotal, double totalProductos, LocalDate fecha, String Usuario) {
        this.productos = productos;
        this.valorTotal = valorTotal;
        this.totalProductos = totalProductos;
        this.fecha = fecha;
        this.Usuario = Usuario;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

}
