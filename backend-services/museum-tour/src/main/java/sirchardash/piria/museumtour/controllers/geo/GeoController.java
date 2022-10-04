package sirchardash.piria.museumtour.controllers.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Country;
import sirchardash.piria.museumtour.services.GeoService;

import java.util.List;

@RestController
class GeoController {

    private final GeoService service;

    @Autowired
    GeoController(GeoService service) {
        this.service = service;
    }

    @GetMapping("/geo/countries")
    public ResponseEntity<List<Country>> country() {
        return ResponseEntity.ok(service.getAllCountries());
    }

    @GetMapping("/geo/cities/{countryName}")
    public ResponseEntity<List<City>> city(@PathVariable String countryName) {
        return ResponseEntity.ok(service.getCities(countryName));
    }

}
