package sirchardash.piria.museumtour.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sirchardash.piria.museumtour.jpa.TourContent.Type.*;

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
        return byStartDate(getTours(museumId), startDate, endDate);
    }

    private List<VirtualTour> byEndDate(List<VirtualTour> tours, LocalDateTime startDate, LocalDateTime endDate) {
        return tours.stream()
                .filter(tour -> tour.getStartTime().isAfter(startDate))
                .filter(tour -> tour.getStartTime().isBefore(endDate))
                .collect(Collectors.toList());
    }

    private List<VirtualTour> byStartDate(List<VirtualTour> tours, LocalDateTime startDate, LocalDateTime endDate) {
        return tours.stream()
                .filter(tour -> tour.getStartTime().isAfter(startDate))
                .filter(tour -> tour.getStartTime().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public List<VirtualTour> getByStartDate(LocalDateTime startDate, LocalDateTime endDate) {
        return byStartDate(repository.findAll(), startDate, endDate);
    }

    public List<VirtualTour> getByEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return byEndDate(repository.findAll(), startDate, endDate);
    }

    public List<VirtualTour> getBooked(String userId) {
        return attendanceRepository.findAllByUserId(userId).stream()
                .map(VirtualTourAttendance::getTour)
                .filter(attendance -> attendance.getEndTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public void addTour(VirtualTour tour,
                        List<MultipartFile> images,
                        List<MultipartFile> videos,
                        List<String> links) {
        try {
            tour.setContent(
                    Stream.of(
                            images.stream().map(file -> toContent(file, IMAGE, tour)).collect(Collectors.toList()),
                            videos.stream().map(file -> toContent(file, VIDEO, tour)).collect(Collectors.toList()),
                            links.stream().map(link -> toContent(link, tour)).collect(Collectors.toList())
                    ).flatMap(List::stream).collect(Collectors.toList())
            );

            repository.saveAndFlush(tour);
        } catch (Exception e) {
            throw new ServiceLogicException(ServiceError.MODEL_REFERENCE_FAILS, 400);
        }
    }

    @SneakyThrows
    private TourContent toContent(MultipartFile file, TourContent.Type type, VirtualTour tour) {
        return new TourContent(
                null,
                file.getBytes(),
                type,
                tour
        );
    }

    private TourContent toContent(String text, VirtualTour tour) {
        return new TourContent(
                null,
                text.getBytes(),
                LINK,
                tour
        );
    }

}
