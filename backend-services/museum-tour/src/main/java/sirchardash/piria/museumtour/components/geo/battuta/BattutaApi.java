package sirchardash.piria.museumtour.components.geo.battuta;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Region;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Component
public class BattutaApi {

    private static final ParameterizedTypeReference<List<BattutaRegion>> regionResponse = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<List<BattutaCity>> cityResponse = new ParameterizedTypeReference<>() {
    };

    private final WebClient webClient;
    private final String apiKey;

    BattutaApi(@Qualifier("battutaWebClient") WebClient webClient,
               @Value("${museum-tour.geo.battuta.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Cacheable("BattutaApi#getRegions")
    public List<Region> getRegions(String countryCode) {
        List<BattutaRegion> regions = webClient.method(GET)
                .uri("/region/{1}/all/?key={2}", countryCode, apiKey)
                .retrieve()
                .bodyToMono(regionResponse)
                .block();

        return regions == null
                ? null
                : regions.stream()
                .map(region -> new Region(region.getName(), region.getCountryCode()))
                .collect(Collectors.toList());
    }

    @Cacheable("BattutaApi#getCities")
    public List<City> getCities(String countryCode, String regionName) {
        List<BattutaCity> cities = webClient.method(GET)
                .uri("/city/{1}/search/?key={2}&region={3}", countryCode, apiKey, regionName)
                .retrieve()
                .bodyToMono(cityResponse)
                .block();

        return cities == null
                ? null
                : cities.stream()
                .map(city -> new City(city.getName(), city.getRegion(), city.getCountryCode()))
                .collect(Collectors.toList());
    }

}
