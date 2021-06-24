package sirchardash.piria.museumtour.components.geo.battuta;

import java.util.List;
import java.util.stream.Collectors;
import javax.naming.ServiceUnavailableException;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Region;

@Component
public class BattutaApi {

    private static final ParameterizedTypeReference<List<BattutaRegion>> regionResponse = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<List<BattutaCity>> cityResponse = new ParameterizedTypeReference<>() {
    };

    private final WebClient webClient;
    private final String apiKey;
    private final Cache<String, List<Region>> regionCache;
    private final Cache<String, List<City>> cityCache;

    BattutaApi(@Qualifier("battutaWebClient") WebClient webClient,
               @Value("${museum-tour.geo.battuta.api-key}") String apiKey,
               Cache<String, List<Region>> regionCache,
               Cache<String, List<City>> cityCache) {
        this.webClient = webClient;
        this.apiKey = apiKey;
        this.regionCache = regionCache;
        this.cityCache = cityCache;
    }

    public List<Region> getRegions(String countryCode) {
        return regionCache.computeIfAbsent(countryCode, x -> getRegionsPrivate(countryCode));
    }

    public List<City> getCities(String countryCode, String regionName) {
        return cityCache.computeIfAbsent(
                countryCode + ":" + regionName,
                x -> getCitiesPrivate(countryCode, regionName)
        );
    }

    private List<Region> getRegionsPrivate(String countryCode) {
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

    private List<City> getCitiesPrivate(String countryCode, String regionName) {
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
