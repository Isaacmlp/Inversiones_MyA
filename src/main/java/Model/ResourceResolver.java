package Model;

import com.itextpdf.text.pdf.codec.Base64;

public interface ResourceResolver {
    Base64.InputStream getResourceAsStream(String uri);
}
