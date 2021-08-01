package sirchardash.piria.museumtour.services;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourRepository;

@Slf4j
@Service
public class TourService {

    private final VirtualTourRepository repository;

    @Autowired
    TourService(VirtualTourRepository repository) {
        this.repository = repository;
    }

    public List<VirtualTour> getTours(int museumId) {
        return repository.findAllByMuseumId(museumId);
    }

    public void addTour(VirtualTour tour) throws ServiceLogicException {
        try {
            repository.saveAndFlush(tour);
        } catch (Exception e) {
            throw new ServiceLogicException(ServiceError.MODEL_REFERENCE_FAILS, 400);
        }
    }

}
