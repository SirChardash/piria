package sirchardash.piria.museumtour.components.weather.openweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import sirchardash.piria.museumtour.models.weather.openweather.OpenWeatherResponse;

@Component
public class OpenWeatherApi {

    private final WebClient webClient;
    private final String apiKey;

    @Autowired
    public OpenWeatherApi(@Qualifier("openweatherWebClient") WebClient webClient,
                          @Value("${museum-tour.weather.openweather.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public OpenWeatherResponse getByCityId(int cityId, String language) {
        return webClient.method(GET)
                .uri("?id={1}&lang={2}&units=metric&appid={3}", cityId, language, apiKey)
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .block();
    }

}
