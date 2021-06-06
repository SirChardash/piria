package sirchardash.piria.museumtour.models;

import java.util.Collection;

public interface RefreshListener<T> {

    void onRefresh(Collection<T> newValues);

}
