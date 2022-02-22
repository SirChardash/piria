package sirchardash.piria.museumtour.components.geo.restcountries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import sirchardash.piria.museumtour.models.geo.Country;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
public class RestCountriesApi {

    private static final ParameterizedTypeReference<List<RestCountry>> response = new ParameterizedTypeReference<>() {
    };

    private final WebClient webClient;

    @Autowired
    RestCountriesApi(@Qualifier("restCountriesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Cacheable("RestCountriesApi#getAllCountries")
    public List<Country> getAllCountries() {
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

    public String toCountryCode(String countryName) {
        return getAllCountries().stream()
                .filter(country -> countryName.equals(country.getName()))
                .findFirst()
                .map(Country::getCode)
                .orElse(null);
    }

}
