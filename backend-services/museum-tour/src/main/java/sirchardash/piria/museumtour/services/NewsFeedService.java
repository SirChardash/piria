package sirchardash.piria.museumtour.services;

import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.huffpost.HuffpostFeed;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.models.FeedEntry;

import java.io.IOException;
import java.util.List;

@Service
public class NewsFeedService {

    private final HuffpostFeed feed;
    private final int maxEntries;

    @Autowired
    NewsFeedService(HuffpostFeed feed,
                    @Value("50") int maxEntries) {
        this.feed = feed;
        this.maxEntries = maxEntries;
    }

    public List<FeedEntry> getEntries() {
        try {
            return feed.getEntries(maxEntries);
        } catch (IOException | FeedException e) {
            throw new ServiceLogicException(ServiceError.SERVICE_UNAVAILABLE, 500);
        }
    }

}
