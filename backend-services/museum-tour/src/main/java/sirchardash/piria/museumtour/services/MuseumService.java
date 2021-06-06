package sirchardash.piria.museumtour.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.museum.MuseumLuceneSearchFactory;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.models.Language;

@Service
@DependsOn("dataRefresh")
public class MuseumService {

    private final MuseumLuceneSearchFactory storage;

    @Autowired
    public MuseumService(MuseumLuceneSearchFactory storage) {
        this.storage = storage;
    }

    public List<Museum> getMuseums(List<String> cities, String searchQuery, Language language) {
        System.out.println(language);
        return storage.getFor(language).find(searchQuery, cities);
    }

}
