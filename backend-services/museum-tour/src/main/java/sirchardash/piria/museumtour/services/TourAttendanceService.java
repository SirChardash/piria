package sirchardash.piria.museumtour.services;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.email.TicketSender;
import sirchardash.piria.museumtour.components.payment.PaymentReportService;
import sirchardash.piria.museumtour.components.tours.TicketIdGenerator;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;
import sirchardash.piria.museumtour.jpa.VirtualTourRepository;
import sirchardash.piria.virtualbank.controllers.paymentreport.Payment;

import java.time.LocalDateTime;

import static sirchardash.piria.museumtour.exceptions.ServiceError.MODEL_REFERENCE_FAILS;
import static sirchardash.piria.museumtour.exceptions.ServiceError.TICKET_NOT_PAID;

@Service
public class TourAttendanceService {

    private final VirtualTourAttendanceRepository attendanceRepository;
    private final VirtualTourRepository tourRepository;
    private final PaymentReportService paymentReportService;
    private final TicketIdGenerator ticketIdGenerator;
    private final TicketSender ticketSender;

    @Autowired
    TourAttendanceService(VirtualTourAttendanceRepository attendanceRepository,
                          VirtualTourRepository tourRepository,
                          PaymentReportService paymentReportService,
                          TicketIdGenerator ticketIdGenerator, TicketSender ticketSender) {
        this.attendanceRepository = attendanceRepository;
        this.tourRepository = tourRepository;
        this.paymentReportService = paymentReportService;
        this.ticketIdGenerator = ticketIdGenerator;
        this.ticketSender = ticketSender;
    }

    public VirtualTour logAttendance(int tourId,
                                     String paymentId,
                                     AccessToken user,
                                     String locale) throws ServiceLogicException {
        VirtualTour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new ServiceLogicException(MODEL_REFERENCE_FAILS, 400));

        String userId = user.getOtherClaims().get("user_id").toString();

        double amountPaid = paymentReportService.get(paymentId).getReportsList().stream()
                .filter(report -> report.getPurpose().equals("attendance of " + userId + " to tour " + tourId))
                .map(Payment::getAmount)
                .reduce(0d, Double::sum);

        if (amountPaid < tour.getTicketPrice()) {
            throw new ServiceLogicException(TICKET_NOT_PAID, 400);
        }

        String ticketId = ticketIdGenerator.generate();
        attendanceRepository.save(new VirtualTourAttendance(
                0,
                tour.getId(),
                userId,
                LocalDateTime.now(),
                ticketId
        ));

        ticketSender.send(ticketId, user.getEmail(), locale);

        return tour;
    }

}
