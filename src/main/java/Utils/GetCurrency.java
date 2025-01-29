package Utils;

import Utils.libs.CurrencyObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetCurrency {
    private final HttpClient client = HttpClient.newHttpClient();
    double paraleloPrice ;
    double bcvPrice ;

    public CurrencyObject getCurrency() {
        String apiUrl = "https://pydolarve.org/api/v1/dollar?page=alcambio";
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
            return new CurrencyObject(57.30, 69.80);
        }

        boolean isResponseOK =
                String
                        .valueOf(response.statusCode())
                        .startsWith("2");

        if (!isResponseOK) {
            // En caso de respuesta no exitosa, devolver valores predeterminados
            return new CurrencyObject(57.30, 69.80);
        }

        final String body = response.body();

        JSONObject bodyJSON = new JSONObject(body);

        JSONObject monitors = bodyJSON.getJSONObject("monitors"); // -> Monitores (BCV, EnParaleloVzla)

        JSONObject bcv = monitors.getJSONObject("bcv");
        JSONObject paralelo = monitors.getJSONObject("enparalelovzla");

        paraleloPrice = paralelo.getDouble("price");
        bcvPrice = bcv.getDouble("price");

        return new CurrencyObject(bcvPrice, paraleloPrice);
    }
}

