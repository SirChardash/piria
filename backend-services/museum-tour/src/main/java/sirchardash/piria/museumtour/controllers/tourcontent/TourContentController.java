package sirchardash.piria.museumtour.controllers.tourcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.models.TourContentEntry;
import sirchardash.piria.museumtour.services.TourContentService;

import java.security.Principal;
import java.util.List;

@RestController
class TourContentController {

    private final TourContentService service;

    @Autowired
    public TourContentController(TourContentService service) {
        this.service = service;
    }

    @GetMapping(value = "/tours/{tourId}/content")
    public ResponseEntity<List<TourContentEntry>> getContentList(@PathVariable("tourId") int tourId,
                                                                 @RequestHeader("x-tour-ticket") String tourTicket,
                                                                 Principal user) {
        return ResponseEntity.ok(service.getContentUrls(tourId, tourTicket, user.getName()));
    }

    @GetMapping(value = "/content/{contentId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getContent(@PathVariable("contentId") int contentId,
                                             @RequestHeader("x-tour-ticket") String tourTicket,
                                             Principal user) {
        return ResponseEntity.ok(service.getContent(contentId, tourTicket, user.getName()));
    }

}
