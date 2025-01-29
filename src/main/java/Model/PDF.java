package Model;
import com.itextpdf.text.pdf.codec.Base64;
import javafx.scene.control.Alert;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.XRLog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class PDF {
    public static void generarPDF(String html, String rutaPDF) {
        try {
            ITextRenderer renderer = new ITextRenderer();


            // Configurar el ResourceResolver para cargar imágenes
            renderer.getSharedContext().setUserAgentCallback(new ResourceResolver("src/main/resources/"));

            renderer.getSharedContext().setBaseURL(System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/Img/");

            // Cargar el HTML como cadena
            renderer.setDocumentFromString(html);

            // Aplicar el layout
            renderer.layout();

            // Generar el PDF
            try (OutputStream outputStream = new FileOutputStream(rutaPDF)) {
                renderer.createPDF(outputStream);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PDF generado");
                alert.setHeaderText("PDF generado exitosamente en: " + rutaPDF);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clase para manejar la carga de recursos (imágenes, CSS, etc.)
    private static class ResourceResolver extends org.xhtmlrenderer.swing.NaiveUserAgent implements Model.ResourceResolver {
        private final String rutaBase;

        public ResourceResolver(String rutaBase) {
            this.rutaBase = rutaBase;
        }

        @Override
        public String resolveURI(String uri) {
            // Si la URI es relativa, prependemos la ruta base
            if (uri.startsWith("/") || uri.startsWith("http://") || uri.startsWith("https://")) {
                return uri; // Rutas absolutas o URLs completas
            } else {
                return rutaBase + uri; // Rutas relativas
            }
        }

        @Override
        public Base64.InputStream getResourceAsStream(String uri) {
            try {
                // Convertir la URI a una URL y abrir un InputStream
                URL url = new URL(resolveURI(uri));
                return (Base64.InputStream) url.openStream();
            } catch (IOException e) {
                XRLog.exception("No se pudo cargar el recurso: " + uri, e);
                return null;
            }
        }
    }

}
