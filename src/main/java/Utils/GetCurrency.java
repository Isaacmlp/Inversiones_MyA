package Utils;

import Utils.libs.CurrencyObject;
import org.json.JSONObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import io.github.cdimascio.dotenv.Dotenv;

public class GetCurrency {
    private final HttpClient client = HttpClient.newHttpClient();
    double paraleloPrice ;
    double bcvPrice ;
    Dotenv dotenv = Dotenv.load();

    public CurrencyObject getCurrency() {
        String apiUrl = dotenv.get("API");
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // En caso de error, devolver valores predeterminados
            return new CurrencyObject(0.0, 0.0);
        }

        boolean isResponseOK =
                String
                        .valueOf(response.statusCode())
                        .startsWith("2");

        if (!isResponseOK) {
            // En caso de respuesta no exitosa, devolver valores predeterminados
            return new CurrencyObject(0.0, 0.0);
        }

        final String body = response.body();

        JSONObject bodyJSON = new JSONObject(body);

        JSONObject bcvCurrency = bodyJSON.getJSONObject("bcv"); // -> Monitores (BCV, EnParaleloVzla)

        //JSONObject bcv = bcvCurrency.getJSONObject("baseValue");
        //JSONObject paralelo = bcvCurrency.getJSONObject("enparalelovzla");

        paraleloPrice = 0.0;
        bcvPrice = Double.parseDouble(String.valueOf(bcvCurrency.getBigDecimal("baseValue")));

        return new CurrencyObject(bcvPrice, paraleloPrice);
    }
}

