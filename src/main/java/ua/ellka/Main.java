package ua.ellka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ellka.client.OpenWeatherClient;
import ua.ellka.client.WeatherClient;
import ua.ellka.service.WeatherService;
import ua.ellka.ui.WeatherAppUi;

import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) {
        String key = System.getenv("OPEN_WEATHER_API_KEY");

        HttpClient client = HttpClient.newBuilder()
                .build();
        WeatherClient weatherClient = new OpenWeatherClient(key,client, new ObjectMapper());
        WeatherService weatherService = new WeatherService(weatherClient);
        WeatherAppUi weatherAppUi = new WeatherAppUi(weatherService);

    }
}