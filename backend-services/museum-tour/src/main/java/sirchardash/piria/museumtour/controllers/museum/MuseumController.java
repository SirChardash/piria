package sirchardash.piria.museumtour.controllers.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.exceptions.DuplicateLanguageEntryException;
import sirchardash.piria.museumtour.exceptions.LanguageNotSupportedException;
import sirchardash.piria.museumtour.models.Confirmation;
import sirchardash.piria.museumtour.models.Language;
import sirchardash.piria.museumtour.services.AddMuseumService;
import sirchardash.piria.museumtour.services.MuseumService;

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

    @PostMapping("/museums")
    public ResponseEntity<Confirmation> addMuseum(@RequestBody NewMuseumRequest request) {
        try {
            addService.addNewMuseum(request.getMuseumLocalizations());
        } catch (DuplicateLanguageEntryException | LanguageNotSupportedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Confirmation(false, e.getMessage()));
        }

        return ResponseEntity.ok(Confirmation.SUCCESS);
    }

}
