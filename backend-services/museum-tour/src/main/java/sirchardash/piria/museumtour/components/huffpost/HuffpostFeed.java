package sirchardash.piria.museumtour.components.huffpost;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.FeedEntry;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HuffpostFeed {

    private final URL source;
    private final FeedEntryFactory factory;
    private final SyndFeedInput input = new SyndFeedInput();

    @SneakyThrows
    @Autowired
    HuffpostFeed(@Value("https://www.huffpost.com/section/arts/feed") String feedUrl,
                 FeedEntryFactory factory) {
        source = new URL(feedUrl);
        this.factory = factory;
    }

    @Cacheable("HuffpostFeed#getEntries")
    @SuppressWarnings("unchecked")
    public List<FeedEntry> getEntries(int maxEntries) throws IOException, FeedException {
        SyndFeed feed = input.build(new XmlReader(source));
        List<SyndEntry> entries = feed.getEntries();

        return entries.stream()
                .map(factory::toEntry)
                .limit(maxEntries)
                .collect(Collectors.toList());
    }

}
