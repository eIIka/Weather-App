package ua.ellka.ui;

import ua.ellka.exception.WeatherAppException;
import ua.ellka.service.WeatherService;
import ua.ellka.model.Weather;

import javax.swing.*;
import java.awt.*;

public class WeatherAppUi extends JFrame {
    private final WeatherService weatherService;

    private JLabel cityLabel = new JLabel("City");
    private JTextField cityTextField = new JTextField(15);
    private JButton getWeatherButton = new JButton("Get Weather");

    private JLabel result = new JLabel("");

    private JLabel temperatureLabel = new JLabel("Temperature");
    private JLabel temperatureValueLabel = new JLabel("");

    private JLabel humidityLabel = new JLabel("Humidity");
    private JLabel humidityValueLabel = new JLabel("");

    {
        setSize(400, 300);
        setTitle("Weather App");
        setLayout(new GridLayout(5, 2));

        add(cityLabel);
        add(cityTextField);

        add(getWeatherButton);
        add(new JLabel());

        add(result);
        add(new JLabel());

        add(temperatureLabel);
        add(temperatureValueLabel);

        add(humidityLabel);
        add(humidityValueLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public WeatherAppUi(WeatherService weatherService) {
        this.weatherService = weatherService;

        getWeatherButton.addActionListener(event -> {
            try {
                String city = cityTextField.getText();

                Weather weatherByCity = weatherService.getWeatherByCity(city);

                temperatureValueLabel.setText(String.valueOf(weatherByCity.getTemp()));
                humidityValueLabel.setText(String.valueOf(weatherByCity.getHumidity()));
            } catch (WeatherAppException e) {
                cityTextField.setText("");
                result.setText(e.getMessage());
                temperatureValueLabel.setText("");
                humidityValueLabel.setText("");
            }
        });
    }
}
