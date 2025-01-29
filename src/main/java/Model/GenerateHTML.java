package Model;

import Model.Facturas.FacturaGenerada;
import Model.Facturas.MetodoPago;
import Utils.GetCurrency;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import Model.Facturas.Producto;

public class GenerateHTML {
    public static String generarHTML(FacturaGenerada facturagenera) {
        GetCurrency getCurrency = new GetCurrency();

        // Configurar el motor de plantillas de Thymeleaf
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("PlantillasHTML/");// Ruta de la plantilla en el classpath
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        // Crear un contexto y agregar los datos
        Context context = new Context();
/*
        Facturagenerada.setNumeroFactura(Integer.parseInt(facturagenera.get(0)));
        Facturagenerada.setFechaFactura(String.valueOf(LocalDate.parse(facturagenera.get(1))));
        Facturagenerada.setTotalBsFactura(Double.parseDouble(facturagenera.get(2)));
        Facturagenerada.setTotalUSDFactura(Double.parseDouble(facturagenera.get(3)));
        Facturagenerada.setEstadoFactura(facturagenera.get(4));

        Facturagenerada.setNombreCliente(facturagenera.get(5));
        Facturagenerada.setCedulaCliente(facturagenera.get(6));
        Facturagenerada.setTelefonoCliente(facturagenera.get(7));
        Facturagenerada.setDireccionCliente(facturagenera.get(8));
        Facturagenerada.setCorreoCliente(facturagenera.get(9));

        ArrayList<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        producto.setIDProducto(Integer.parseInt(facturagenera.get(11)));
        producto.setNombreProducto(facturagenera.get(12));
        producto.setCantidad(Integer.parseInt(facturagenera.get(13)));
        producto.setPrecioUnitarioBs(Double.parseDouble(facturagenera.get(14)));
        producto.setPrecioUnitarioUSD(Double.parseDouble(facturagenera.get(15)));
        producto.setSubtotalBs(Double.parseDouble(facturagenera.get(16)));
        producto.setSubtotalUSD(Double.parseDouble(facturagenera.get(17)));

        productos.add(producto);


        ArrayList<MetodoPago> metodoPagos = new ArrayList<>();
        MetodoPago metodo = new MetodoPago();

        metodo.setIDMetodoPago(Integer.parseInt(facturagenera.get(18)));
        metodo.setMetodoPago(facturagenera.get(19));
        metodo.setMontoPagado(Double.parseDouble(facturagenera.get(20)));

        metodoPagos.add()

        Facturagenerada.setPago(metodoPagos);

        Facturagenerada.setTotalPagadoBs(Double.parseDouble(facturagenera.get(21)));
        Facturagenerada.setTotalPagadoUSD((Double.parseDouble(facturagenera.get(21)) / getCurrency.getCurrency().bcv()));

*/




        context.setVariable("factura", facturagenera);

        // Procesar la plantilla
        StringWriter writer = new StringWriter();

        // Obtener el HTML generado
        String html = writer.toString();
        System.out.println(html);

        // Procesar la plantilla y devolver el HTML generado
        return templateEngine.process("FacturaPlantilla", context);
    }
}
