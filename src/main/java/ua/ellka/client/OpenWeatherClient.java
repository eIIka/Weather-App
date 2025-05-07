package ua.ellka.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ua.ellka.client.resp.OpenWeatherResp;
import ua.ellka.exception.WeatherAppException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class OpenWeatherClient implements WeatherClient {
    private static final String URL = "https://api.openweathermap.org/data/2.5";

    private final String apiKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Override
    public OpenWeatherResp getCurrentWeatherDataByQuery(String city) throws WeatherAppException {
        try {
            URI uri = new URI(URL + String.format("/weather?appid=%s&q=%s&units=metrics", apiKey, city));

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new WeatherAppException("Server returned an error");
            }

            return objectMapper.readValue(response.body(), OpenWeatherResp.class);
        } catch (URISyntaxException e) {
            System.out.println("Incorrect URL: " + e.getMessage());
            throw new WeatherAppException("Incorrect URL");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error when requesting weather serve: " + e.getMessage());
            throw new WeatherAppException("Error when requesting weather serve");
        }
    }
}
