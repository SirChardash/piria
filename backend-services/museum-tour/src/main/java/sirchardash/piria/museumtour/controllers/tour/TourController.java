package sirchardash.piria.museumtour.controllers.tour;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.models.Confirmation;
import sirchardash.piria.museumtour.models.ConfirmationResponse;
import sirchardash.piria.museumtour.services.TourService;

import java.security.Principal;
import java.time.LocalDateTime;

@Slf4j
@RestController
class TourController {

    private final TourService service;

    @Autowired
    TourController(TourService service) {
        this.service = service;
    }

    @GetMapping("/tours/{museumId}")
    ResponseEntity<TourResponse> getToursForMuseum(@PathVariable int museumId) {
        return ResponseEntity.ok(new TourResponse(service.getTours(museumId)));
    }

    @PostMapping("/tours")
    ResponseEntity<ConfirmationResponse> addTour(@RequestBody VirtualTour tour) {
        try {
            service.addTour(tour);
            return ResponseEntity.ok(ConfirmationResponse.SUCCESS);
        } catch (ServiceLogicException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(new ConfirmationResponse(new Confirmation(false, e.getServiceError().getCode())));
        }
    }

    @GetMapping("/tours/upcoming")
    ResponseEntity<TourResponse> getUpcomingTours() {
        return ResponseEntity.ok(
                new TourResponse(service.getByDate(LocalDateTime.now(), LocalDateTime.now().plusDays(30)))
        );
    }

    @GetMapping("/tours/previous")
    ResponseEntity<TourResponse> getToursForPastMonth() {
        return ResponseEntity.ok(
                new TourResponse(service.getByDate(LocalDateTime.now().minusDays(30), LocalDateTime.now()))
        );
    }

    @GetMapping("/tours/booked")
    ResponseEntity<TourResponse> getBookedTours(Principal user) {
        System.out.println(user.getName());
        return ResponseEntity.ok(new TourResponse(service.getForUser(user.getName())));
    }

}
