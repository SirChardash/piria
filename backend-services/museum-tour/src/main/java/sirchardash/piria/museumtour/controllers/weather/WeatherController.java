package sirchardash.piria.museumtour.controllers.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.services.WeatherService;

@CrossOrigin("*")
@RestController
public class WeatherController {

    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam @Validated String countryCode,
                                      @RequestParam @Validated String language) {
        return new WeatherResponse(service.getWeather(countryCode, language));
    }
}
