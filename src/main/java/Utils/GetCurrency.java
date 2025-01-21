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
            throw new RuntimeException("Error llamando con la API");
        }

        boolean isResponseOK =
                String
                        .valueOf(response.statusCode())
                        .startsWith("2");

        if (!isResponseOK) {
            throw new RuntimeException("No se puede procesar la solicitud");
        }

        final String body = response.body();

        JSONObject bodyJSON = new JSONObject(body);

        JSONObject monitors = bodyJSON.getJSONObject("monitors"); // -> Monitores (BCV, EnParaleloVzla)

        JSONObject bcv = monitors.getJSONObject("bcv");
        JSONObject paralelo = monitors.getJSONObject("enparalelovzla");

        double paraleloPrice = paralelo.getDouble("price");
        double bcvPrice = bcv.getDouble("price");

        return new CurrencyObject(bcvPrice, paraleloPrice);
    }
}

