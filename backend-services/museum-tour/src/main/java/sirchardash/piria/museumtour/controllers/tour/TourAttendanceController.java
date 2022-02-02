package sirchardash.piria.museumtour.controllers.tour;

import org.keycloak.common.VerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.components.auth.TokenParser;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
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
    ResponseEntity<Void> logAttendance(@PathVariable int tourId,
                                       @PathVariable String paymentId,
                                       @RequestHeader String authorization) {
        try {
            service.logAttendance(tourId, paymentId, parser.fromHeader(authorization));
            return ResponseEntity.status(200).build();
        } catch (VerificationException e) {
            return ResponseEntity.status(400).build();
        } catch (ServiceLogicException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}
