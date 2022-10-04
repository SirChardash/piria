package sirchardash.piria.museumtour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.TourContent;
import sirchardash.piria.museumtour.jpa.TourContentRepository;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourRepository;
import sirchardash.piria.museumtour.models.TourContentEntry;

import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourContentService {

    private final VirtualTourRepository virtualTourRepository;
    private final TourContentRepository tourContentRepository;

    @Autowired
    public TourContentService(VirtualTourRepository virtualTourRepository,
                              TourContentRepository tourContentRepository) {
        this.virtualTourRepository = virtualTourRepository;
        this.tourContentRepository = tourContentRepository;
    }

    public List<TourContentEntry> getContentUrls(int tourId,
                                                 String tourTicket,
                                                 String userId) {
        VirtualTour tour = virtualTourRepository.findById(tourId).orElseThrow(NotFoundException::new);

        if (noAttendance(tour, tourTicket, userId)) {
            throw new ServiceLogicException(ServiceError.ATTENDANCE_FORBIDDEN, 403);
        }

        return tour.getContent()
                .stream()
                .map(content -> new TourContentEntry(
                        String.format("/content/%d", content.getId()),
                        content.getType().name()
                )).collect(Collectors.toList());
    }

    public byte[] getContent(int contentId, String tourTicket, String userId) {
        TourContent content = tourContentRepository.getOne(contentId);

        if (noAttendance(content.getVirtualTour(), tourTicket, userId)) {
            throw new ServiceLogicException(ServiceError.ATTENDANCE_FORBIDDEN, 403);
        }

        LocalDateTime now = LocalDateTime.now();
        if (content.getVirtualTour().getEndTime().isBefore(now)
                || content.getVirtualTour().getStartTime().isAfter(now)) {
            throw new ServiceLogicException(ServiceError.CONTENT_UNAVAILABLE_OUTSIDE_TIMEFRAME, 403);
        }

        return content.getContent();
    }

    private static boolean noAttendance(VirtualTour tour, String tourTicket, String userId) {
        return tour.getAttendances().stream()
                .noneMatch(attendance -> attendance.getTicketId().equals(tourTicket)
                        && attendance.getUserId().equals(userId)
                );
    }

}
