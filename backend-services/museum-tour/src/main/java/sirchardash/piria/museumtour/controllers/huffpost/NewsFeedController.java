package sirchardash.piria.museumtour.controllers.huffpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.models.FeedEntry;
import sirchardash.piria.museumtour.services.NewsFeedService;

import java.util.List;

@RestController
class NewsFeedController {

    private final NewsFeedService service;

    @Autowired
    NewsFeedController(NewsFeedService service) {
        this.service = service;
    }

    @GetMapping("/news")
    ResponseEntity<List<FeedEntry>> getNews() throws ServiceLogicException {
        return ResponseEntity.ok(service.getEntries());
    }

}
