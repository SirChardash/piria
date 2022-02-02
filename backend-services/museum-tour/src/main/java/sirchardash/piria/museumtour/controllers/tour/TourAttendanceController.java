package sirchardash.piria.museumtour.controllers.tour;

import org.keycloak.common.VerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirchardash.piria.museumtour.components.auth.TokenParser;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.services.TourAttendanceService;

@RestController
class TourAttendanceController {

    private final TourAttendanceService service;
    private final TokenParser parser;

    @Autowired
    TourAttendanceController(TourAttendanceService service,
                             TokenParser parser) {
        this.service = service;
        this.parser = parser;
    }

    @PostMapping("/attendance/{tourId}/{paymentId}")
    ResponseEntity<VirtualTour> logAttendance(@PathVariable int tourId,
                                              @PathVariable String paymentId,
                                              @RequestHeader String authorization,
                                              @RequestParam(defaultValue = "en") String locale) {
        try {
            return ResponseEntity.status(200).body(service.logAttendance(
                    tourId,
                    paymentId,
                    parser.fromHeader(authorization),
                    locale
            ));
        } catch (VerificationException e) {
            return ResponseEntity.status(400).build();
        } catch (ServiceLogicException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}
