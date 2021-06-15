package sirchardash.piria.museumtour.components.weather.openweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OpenWeatherServiceDelegate {

    @Bean("openweatherWebClient")
    public WebClient webClient(WebClient.Builder builder,
                               @Value("${museum-tour.weather.openweather.base-url}") String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }

}
