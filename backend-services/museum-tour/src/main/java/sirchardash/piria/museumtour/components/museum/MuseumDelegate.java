package sirchardash.piria.museumtour.components.museum;

import org.springframework.beans.factory.annotation.Qualifier;
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
    public MuseumLuceneSearch luceneSearchEn(@Qualifier("museumRefreshEn") MuseumRefresh refresh) {
        return new MuseumLuceneSearch(refresh);
    }

    @Bean("museumLuceneSearchSr")
    public MuseumLuceneSearch luceneSearchSr(@Qualifier("museumRefreshSr") MuseumRefresh refresh) {
        return new MuseumLuceneSearch(refresh);
    }

}
