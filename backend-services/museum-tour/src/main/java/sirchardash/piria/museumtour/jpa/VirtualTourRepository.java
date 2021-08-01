package sirchardash.piria.museumtour.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualTourRepository extends JpaRepository<VirtualTour, Integer> {

    List<VirtualTour> findAllByMuseumId(int museumId);

}
