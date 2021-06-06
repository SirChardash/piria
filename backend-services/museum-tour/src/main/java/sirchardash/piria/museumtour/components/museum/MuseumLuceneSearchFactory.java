package sirchardash.piria.museumtour.components.museum;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.Language;

@Component
public class MuseumLuceneSearchFactory {

    private final Map<Language, MuseumLuceneSearch> searchMap;

    @Autowired
    public MuseumLuceneSearchFactory(@Qualifier("museumLuceneSearchEn") MuseumLuceneSearch storageEn,
                                     @Qualifier("museumLuceneSearchSr") MuseumLuceneSearch storageSr) {
        searchMap = Map.of(
                Language.ENGLISH, storageEn,
                Language.SERBIAN, storageSr
        );
    }

    public MuseumLuceneSearch getFor(Language language) {
        return searchMap.getOrDefault(language, searchMap.get(Language.ENGLISH));
    }

}
