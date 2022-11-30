package sirchardash.piria.museumtour.controllers.tour;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.models.ConfirmationResponse;
import sirchardash.piria.museumtour.services.TourService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/tours/upcoming/{museumId}")
    ResponseEntity<TourResponse> getUpcomingToursForMuseum(@PathVariable int museumId) {
        return ResponseEntity.ok(new TourResponse(service.getTours(
                museumId,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30)
        )));
    }

    @PostMapping("/tours")
    ResponseEntity<ConfirmationResponse> addTour(@RequestParam(value = "image", required = false) List<MultipartFile> images,
                                                 @RequestParam(value = "video", required = false) MultipartFile video,
                                                 @RequestParam(value = "link", required = false) String link,
                                                 @ModelAttribute VirtualTour tour) {
        if (images == null || images.size() < 5 || images.size() > 10 || (video == null) == (link == null)) {
            return ResponseEntity.status(400).build();
        }

        service.addTour(
                tour,
                images,
                video != null ? List.of(video) : Collections.emptyList(),
                link != null ? List.of(link) : Collections.emptyList()
        );

        return ResponseEntity.ok(ConfirmationResponse.SUCCESS);
    }

    @GetMapping("/tours/upcoming")
    ResponseEntity<TourResponse> getUpcomingTours() {
        return ResponseEntity.ok(
                new TourResponse(service.getByStartDate(LocalDateTime.now(), LocalDateTime.now().plusDays(30)))
        );
    }

    @GetMapping("/tours/previous")
    ResponseEntity<TourResponse> getToursForPastMonth() {
        return ResponseEntity.ok(
                new TourResponse(service.getByEndDate(LocalDateTime.now().minusDays(30), LocalDateTime.now()))
        );
    }

    @GetMapping("/tours/booked")
    ResponseEntity<TourResponse> getBookedTours(Principal user) {
        return ResponseEntity.ok(new TourResponse(service.getForUser(user.getName())));
    }

}
