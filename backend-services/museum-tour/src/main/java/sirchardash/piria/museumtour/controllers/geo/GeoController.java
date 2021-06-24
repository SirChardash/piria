package sirchardash.piria.museumtour.controllers.geo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Country;
import sirchardash.piria.museumtour.models.geo.Region;
import sirchardash.piria.museumtour.services.GeoService;

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

    @GetMapping("/geo/regions/{countryCode}")
    public ResponseEntity<List<Region>> region(@PathVariable String countryCode) {
        return ResponseEntity.ok(service.getRegions(countryCode));
    }

    @GetMapping("/geo/cities/{countryCode}/{region}")
    public ResponseEntity<List<City>> city(@PathVariable String countryCode,
                                           @PathVariable String region) {
        return ResponseEntity.ok(service.getCities(countryCode, region));
    }

}
