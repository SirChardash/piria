package sirchardash.piria.museumtour.components.geo.restcountries;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
class RestCountriesServiceDelegate {

    @Bean("restCountriesWebClient")
    WebClient webClient(WebClient.Builder builder,
                               @Value("${museum-tour.geo.rest-countries.base-url}") String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }

}
