package sirchardash.piria.museumtour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.geo.battuta.BattutaApi;
import sirchardash.piria.museumtour.components.geo.restcountries.RestCountriesApi;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Country;

import java.util.List;

@Service
public class GeoService {

    private final RestCountriesApi restCountriesApi;
    private final BattutaApi battutaApi;

    @Autowired
    GeoService(RestCountriesApi restCountriesApi,
               BattutaApi battutaApi) {
        this.restCountriesApi = restCountriesApi;
        this.battutaApi = battutaApi;
    }

    public List<Country> getAllCountries() {
        return restCountriesApi.getAllCountries();
    }

    public List<City> getCities(String countryName) throws ServiceLogicException {
        return battutaApi.getCities(countryNameToCode(countryName));
    }

    private String countryNameToCode(String countryName) throws ServiceLogicException {
        return getAllCountries().stream()
                .filter(country -> country.getName().equals(countryName))
                .findFirst()
                .map(Country::getCode)
                .orElseThrow(() -> new ServiceLogicException(null, 404));
    }

}
