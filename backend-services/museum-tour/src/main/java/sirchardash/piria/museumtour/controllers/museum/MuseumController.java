package sirchardash.piria.museumtour.controllers.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.Language;
import sirchardash.piria.museumtour.services.MuseumService;

@RestController
public class MuseumController {

    private final MuseumService service;

    @Autowired
    public MuseumController(MuseumService service) {
        this.service = service;
    }

    @GetMapping("/museums")
    public ResponseEntity<MuseumResponse> museums(@RequestParam(defaultValue = "en") String language,
                                                  @RequestParam(defaultValue = "") String query) {
        return ResponseEntity.ok(
                new MuseumResponse(service.getMuseums(query, Language.forCode(language)))
        );
    }
}
