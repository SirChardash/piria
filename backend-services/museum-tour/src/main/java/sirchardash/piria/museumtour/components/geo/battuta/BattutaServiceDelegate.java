package sirchardash.piria.museumtour.components.geo.battuta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
class BattutaServiceDelegate {

    @Bean("battutaWebClient")
    WebClient webClient(WebClient.Builder builder,
                               @Value("${museum-tour.geo.battuta.base-url}") String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }

}
