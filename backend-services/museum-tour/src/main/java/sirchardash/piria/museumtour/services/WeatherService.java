package sirchardash.piria.museumtour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.weather.WeatherFactory;
import sirchardash.piria.museumtour.components.weather.openweather.CountryToCities;
import sirchardash.piria.museumtour.components.weather.openweather.OpenWeatherApi;
import sirchardash.piria.museumtour.models.weather.Weather;
import sirchardash.piria.museumtour.models.weather.openweather.City;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final OpenWeatherApi openWeatherApi;
    private final CountryToCities countryToCities;
    private final WeatherFactory factory;
    private final int citiesPerRequest;

    @Autowired
    public WeatherService(OpenWeatherApi openWeatherApi,
                          CountryToCities countryToCities,
                          WeatherFactory factory,
                          @Value("${museum-tour.weather.cities-per-request}") int citiesPerRequest) {
        this.openWeatherApi = openWeatherApi;
        this.countryToCities = countryToCities;
        this.factory = factory;
        this.citiesPerRequest = citiesPerRequest;
    }

    public List<Weather> getWeather(String country, String language) {
        List<City> cities = countryToCities.getCities(country);
        Collections.shuffle(cities);
        return cities.stream()
                .limit(citiesPerRequest)
                .map(City::getId)
                .map(cityId -> openWeatherApi.getByCityId(cityId, language))
                .map(factory::fromOpenWeather)
                .collect(Collectors.toList());
    }

}
