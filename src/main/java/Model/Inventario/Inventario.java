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


    public LocalDate getFecha() {
        return fecha;
    }


}
