package sirchardash.piria.museumtour.components.museum;

import java.io.IOException;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.jpa.MuseumRepository;
import sirchardash.piria.museumtour.models.Language;

@Component
public class MuseumDelegate {

    @Bean("museumRefreshEn")
    public MuseumRefresh storageEn(MuseumRepository repository) {
        return new MuseumRefresh(repository, Language.ENGLISH);
    }

    @Bean("museumRefreshSr")
    public MuseumRefresh storageSr(MuseumRepository repository) {
        return new MuseumRefresh(repository, Language.SERBIAN);
    }

    @Bean("museumLuceneSearchEn")
    public MuseumLuceneSearch luceneSearchEn(@Qualifier("museumRefreshEn") MuseumRefresh refresh,
                                             @Value("${museum-tour.museum.max-returned}") int maxReturned) throws IOException {
        return new MuseumLuceneSearch(refresh, Path.of("lucene/en"), maxReturned);
    }

    @Bean("museumLuceneSearchSr")
    public MuseumLuceneSearch luceneSearchSr(@Qualifier("museumRefreshSr") MuseumRefresh refresh,
                                             @Value("${museum-tour.museum.max-returned}") int maxReturned) throws IOException {
        return new MuseumLuceneSearch(refresh, Path.of("lucene/sr"), maxReturned);
    }

}
