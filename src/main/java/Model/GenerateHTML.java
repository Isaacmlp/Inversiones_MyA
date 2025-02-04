package Model;

import Model.Facturas.FacturaGenerada;
import Model.Inventario.Inventario;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.StringWriter;


public class GenerateHTML {
    public static String generarHTML(FacturaGenerada facturagenera) {

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

        context.setVariable("factura", facturagenera);

        // Procesar la plantilla
        StringWriter writer = new StringWriter();

        // Obtener el HTML generado
        String html = writer.toString();
        System.out.println(html);

        // Procesar la plantilla y devolver el HTML generado
        return templateEngine.process("FacturaPlantilla", context);
    }

    public static String generarReporte(Inventario reporteInventario) {

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

        context.setVariable("inventario", reporteInventario);

        // Procesar la plantilla
        StringWriter writer = new StringWriter();

        // Obtener el HTML generado
        String html = writer.toString();
        System.out.println(html);

        // Procesar la plantilla y devolver el HTML generado
        return templateEngine.process("ReporteInventarioPlantilla", context);
    }
}
