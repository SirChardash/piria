package sirchardash.piria.museumtour.components.geo.restcountries;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import sirchardash.piria.museumtour.models.geo.Country;

@Slf4j
@Component
public class RestCountriesApi {

    private static final String ALL = "all";
    private static final ParameterizedTypeReference<List<RestCountry>> response = new ParameterizedTypeReference<>() {
    };

    private final WebClient webClient;
    private final Cache<String, List<Country>> cache;

    @Autowired
    RestCountriesApi(@Qualifier("restCountriesWebClient") WebClient webClient,
                     Cache<String, List<Country>> cache) {
        this.webClient = webClient;
        this.cache = cache;
    }

    public List<Country> getAllCountries() {
        return cache.computeIfAbsent(ALL, x -> get());
    }

    private List<Country> get() {
        List<RestCountry> countries = webClient.method(GET)
                .retrieve()
                .bodyToMono(response)
                .block();

        return countries == null
                ? null
                : countries.stream()
                .filter(country -> "Europe".equals(country.getRegion()))
                .map(country -> new Country(country.getName(), country.getCountryCode().toLowerCase()))
                .collect(Collectors.toList());
    }

}
