package sirchardash.piria.museumtour.components.weather.openweather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.weather.openweather.City;

@Component
public class CountryToCities {

    private final Map<String, List<City>> map;

    @Autowired
    public CountryToCities(@Value("${museum-tour.weather.openweather.city-list-path}") String cityListPath,
                           ObjectMapper json) throws IOException {
        map = json.readValue(new File(cityListPath), new TypeReference<List<City>>() {
        }).stream().collect(Collectors.groupingBy(City::getCountry));
    }

    public List<City> getCities(String country) {
        return map.getOrDefault(country, Collections.emptyList());
    }

}
