package sirchardash.piria.museumtour.components.museum;

import java.util.ArrayList;
import java.util.List;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.jpa.MuseumRepository;
import sirchardash.piria.museumtour.models.Language;
import sirchardash.piria.museumtour.models.RefreshListener;

public class MuseumRefresh {

    private final MuseumRepository repository;
    private final Language language;
    private final List<RefreshListener<Museum>> refreshListeners = new ArrayList<>();

    MuseumRefresh(MuseumRepository repository,
                  Language language) {
        this.repository = repository;
        this.language = language;
    }

    public void addRefreshListener(RefreshListener<Museum> refreshListener) {
        refreshListeners.add(refreshListener);
    }

    public void refresh() {
        List<Museum> newState = repository.findAllByLanguage(language.getCode());
        refreshListeners.forEach(listener -> listener.onRefresh(newState));
    }

}
