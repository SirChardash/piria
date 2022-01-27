package sirchardash.piria.museumtour.components.huffpost;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.FeedEntry;

@Component
class FeedEntryFactory {

    FeedEntry toEntry(SyndEntry entry) {
        return new FeedEntry(
                entry.getTitle().trim(),
                ((SyndContent) entry.getContents().get(0)).getValue().trim(),
                ((SyndEnclosure) entry.getEnclosures().get(0)).getUrl(),
                entry.getLink()
        );
    }

}
