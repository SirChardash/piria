package sirchardash.piria.museumtour.controllers.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.tracking.TrackingLog;
import sirchardash.piria.museumtour.services.TrackingService;

import javax.validation.Valid;

@RestController
class TrackingController {

    private final TrackingService service;

    @Autowired
    TrackingController(TrackingService service) {
        this.service = service;
    }

    @PostMapping("/tracking")
    public void saveTrackingLogs(@Valid @RequestBody TrackingLog log) {
        service.save(log);
    }

}
