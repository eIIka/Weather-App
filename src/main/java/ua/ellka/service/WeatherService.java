package ua.ellka.service;

import lombok.RequiredArgsConstructor;
import ua.ellka.client.WeatherClient;
import ua.ellka.client.resp.OpenWeatherResp;
import ua.ellka.exception.WeatherAppException;
import ua.ellka.model.Weather;

@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;

    public Weather getWeatherByCity(String city) throws WeatherAppException {
        OpenWeatherResp currentWeatherDataByQuery = weatherClient.getCurrentWeatherDataByQuery(city);

        double temp = currentWeatherDataByQuery.getMain().getTemp() - 273.15;
        double roundedTemp = Math.round(temp * 100.0) / 100.0;
        double humidity = currentWeatherDataByQuery.getMain().getHumidity();

        return new Weather(roundedTemp, humidity);
    }
}
