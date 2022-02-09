package sirchardash.piria.museumtour.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;
import sirchardash.piria.museumtour.jpa.VirtualTourRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourService {

    private final VirtualTourRepository repository;
    private final VirtualTourAttendanceRepository attendanceRepository;

    @Autowired
    TourService(VirtualTourRepository repository,
                VirtualTourAttendanceRepository attendanceRepository) {
        this.repository = repository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<VirtualTour> getTours(int museumId) {
        return repository.findAllByMuseumId(museumId);
    }

    public List<VirtualTour> getTours(int museumId, LocalDateTime startDate, LocalDateTime endDate) {
        return byDate(getTours(museumId), startDate, endDate);
    }

    private List<VirtualTour> byDate(List<VirtualTour> tours, LocalDateTime startDate, LocalDateTime endDate) {
        return tours.stream()
                .filter(tour -> tour.getStartTime().isAfter(startDate))
                .filter(tour -> tour.getStartTime().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public List<VirtualTour> getByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return byDate(repository.findAll(), startDate, endDate);
    }

    public List<VirtualTour> getForUser(String userId) {
        return attendanceRepository.findAllByUserId(userId).stream()
                .map(VirtualTourAttendance::getTourId)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void addTour(VirtualTour tour) throws ServiceLogicException {
        try {
            repository.saveAndFlush(tour);
        } catch (Exception e) {
            throw new ServiceLogicException(ServiceError.MODEL_REFERENCE_FAILS, 400);
        }
    }

}
