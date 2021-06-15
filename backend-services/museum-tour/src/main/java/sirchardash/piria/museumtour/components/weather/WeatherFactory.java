package sirchardash.piria.museumtour.components.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.weather.Weather;
import sirchardash.piria.museumtour.models.weather.openweather.OpenWeatherResponse;

@Component
public class WeatherFactory {

    private final String openweatherImageUrlTemplate;

    @Autowired
    public WeatherFactory(@Value("${museum-tour.weather.open-weather.image-url}") String openweatherImageUrlTemplate) {
        this.openweatherImageUrlTemplate = openweatherImageUrlTemplate;
    }

    public Weather fromOpenWeather(OpenWeatherResponse response) {
        sirchardash.piria.museumtour.models.weather.openweather.Weather weather = response.getWeather().get(0);
        return new Weather(
                response.getCityName(),
                weather.getDescription(),
                String.format(openweatherImageUrlTemplate, weather.getIcon()),
                String.format("%.1f℃", response.getStats().getTemperature()),
                String.format("%.1f℃", response.getStats().getFeelsLikeTemperature())
        );
    }
}
