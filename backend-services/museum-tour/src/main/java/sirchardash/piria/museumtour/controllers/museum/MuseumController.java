package sirchardash.piria.museumtour.controllers.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.models.ConfirmationResponse;
import sirchardash.piria.museumtour.models.Language;
import sirchardash.piria.museumtour.services.AddMuseumService;
import sirchardash.piria.museumtour.services.MuseumService;

import javax.validation.Valid;

@RestController
public class MuseumController {

    private final MuseumService service;
    private final AddMuseumService addService;

    @Autowired
    public MuseumController(MuseumService service,
                            AddMuseumService addService) {
        this.service = service;
        this.addService = addService;
    }

    @GetMapping("/museums")
    public ResponseEntity<MuseumResponse> museums(@RequestParam(defaultValue = "en") String language,
                                                  @RequestParam(defaultValue = "") String query) {
        return ResponseEntity.ok(
                new MuseumResponse(service.getMuseums(query, Language.forCode(language)))
        );
    }

    @GetMapping("/museums/{id}")
    public ResponseEntity<Museum> getMuseumById(@PathVariable int id) {
        return ResponseEntity.ok(service.getMuseum(id));
    }

    @PostMapping("/museums")
    public ResponseEntity<ConfirmationResponse> addMuseum(@RequestBody @Valid NewMuseumRequest request) {
        addService.addNewMuseum(request.getMuseumLocalizations());

        return ResponseEntity.ok(ConfirmationResponse.SUCCESS);
    }

}
