package sirchardash.piria.museumtour.services;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.email.TicketSender;
import sirchardash.piria.museumtour.components.payment.PaymentReportService;
import sirchardash.piria.museumtour.components.tours.TicketIdGenerator;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.*;
import sirchardash.piria.virtualbank.controllers.paymentreport.Payment;

import java.time.LocalDateTime;
import java.util.Optional;

import static sirchardash.piria.museumtour.exceptions.ServiceError.MODEL_REFERENCE_FAILS;
import static sirchardash.piria.museumtour.exceptions.ServiceError.TICKET_NOT_PAID;

@Service
public class TourAttendanceService {

    private final VirtualTourAttendanceRepository attendanceRepository;
    private final VirtualTourRepository tourRepository;
    private final MuseumRepository museumRepository;
    private final PaymentReportService paymentReportService;
    private final TicketIdGenerator ticketIdGenerator;
    private final TicketSender ticketSender;

    @Autowired
    TourAttendanceService(VirtualTourAttendanceRepository attendanceRepository,
                          VirtualTourRepository tourRepository,
                          MuseumRepository museumRepository,
                          PaymentReportService paymentReportService,
                          TicketIdGenerator ticketIdGenerator,
                          TicketSender ticketSender) {
        this.attendanceRepository = attendanceRepository;
        this.tourRepository = tourRepository;
        this.museumRepository = museumRepository;
        this.paymentReportService = paymentReportService;
        this.ticketIdGenerator = ticketIdGenerator;
        this.ticketSender = ticketSender;
    }

    public VirtualTour logAttendance(int tourId,
                                     String paymentId,
                                     AccessToken user,
                                     String locale) {
        VirtualTour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new ServiceLogicException(MODEL_REFERENCE_FAILS, 400));

        String userId = user.getOtherClaims().get("user_id").toString();

        Optional<VirtualTourAttendance> existingAttendance = attendanceRepository.findByTourIdAndUserId(tourId, userId);

        VirtualTourAttendance attendance;

        if (existingAttendance.isPresent()) {
            attendance = existingAttendance.get();
        } else {
            double amountPaid = paymentReportService.get(paymentId).getReportsList().stream()
                    .filter(report -> report.getPurpose().equals("attendance of " + userId + " to tour " + tourId))
                    .map(Payment::getAmount)
                    .reduce(0d, Double::sum);

            if (amountPaid < tour.getTicketPrice()) {
                throw new ServiceLogicException(TICKET_NOT_PAID, 400);
            }

            String ticketId = ticketIdGenerator.generate();
            attendance = new VirtualTourAttendance(
                    0,
                    tour,
                    userId,
                    LocalDateTime.now(),
                    ticketId,
                    false,
                    false
            );
            attendanceRepository.save(attendance);
        }

        ticketSender.send(locale, user, museumRepository.getOne(tourId), tour, attendance);
        return tour;
    }

}
