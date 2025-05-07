package ua.ellka.client;

import ua.ellka.client.resp.OpenWeatherResp;
import ua.ellka.exception.WeatherAppException;

public interface WeatherClient {
    OpenWeatherResp getCurrentWeatherDataByQuery(String city) throws WeatherAppException;
}
