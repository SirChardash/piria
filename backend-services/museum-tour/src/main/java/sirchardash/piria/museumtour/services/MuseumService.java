package sirchardash.piria.museumtour.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.museum.MuseumLuceneSearchFactory;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.jpa.MuseumRepository;
import sirchardash.piria.museumtour.models.Language;

@Service
@DependsOn("dataRefresh")
public class MuseumService {

    private final MuseumLuceneSearchFactory storage;
    private final MuseumRepository repository;

    @Autowired
    public MuseumService(MuseumLuceneSearchFactory storage,
                         MuseumRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    public List<Museum> getMuseums(String searchQuery, Language language) {
        return storage.getFor(language).find(searchQuery);
    }

    public Museum getMuseum(int id) throws ServiceLogicException {
        Optional<Museum> museum = repository.findById(id);
        if (museum.isPresent()) {
            return museum.get();
        } else {
            throw new ServiceLogicException(null, 404);
        }
    }

}
