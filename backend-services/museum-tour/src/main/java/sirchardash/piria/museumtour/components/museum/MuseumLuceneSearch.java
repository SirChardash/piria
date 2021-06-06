package sirchardash.piria.museumtour.components.museum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.models.RefreshListener;

public class MuseumLuceneSearch implements RefreshListener<Museum> {

    private List<Museum> museums = List.of();

    MuseumLuceneSearch(MuseumRefresh refresh) {
        refresh.addRefreshListener(this);
    }

    @Override
    public void onRefresh(Collection<Museum> newValues) {
        museums = new ArrayList<>(newValues);
    }

    public List<Museum> find(String query, List<String> cities) {
        return museums;
    }

}
