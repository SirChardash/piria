package sirchardash.piria.museumtour.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumRepository extends JpaRepository<Museum, Integer> {

    List<Museum> findAllByLanguage(String language);

}
