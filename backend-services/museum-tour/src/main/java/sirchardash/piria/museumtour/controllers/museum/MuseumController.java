package sirchardash.piria.museumtour.controllers.museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.models.Confirmation;
import sirchardash.piria.museumtour.models.ConfirmationResponse;
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
    public ResponseEntity<ConfirmationResponse> addMuseum(@RequestBody NewMuseumRequest request) {
        try {
            addService.addNewMuseum(request.getMuseumLocalizations());
        } catch (ServiceLogicException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ConfirmationResponse(new Confirmation(false, e.getServiceError().getCode())));
        }

        return ResponseEntity.ok(ConfirmationResponse.SUCCESS);
    }

}
