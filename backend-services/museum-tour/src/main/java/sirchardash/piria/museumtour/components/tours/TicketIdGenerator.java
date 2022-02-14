package sirchardash.piria.museumtour.components.tours;

import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.components.random.RandomString;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketIdGenerator {

    private final VirtualTourAttendanceRepository repository;
    private final RandomString randomString;

    public TicketIdGenerator(VirtualTourAttendanceRepository repository, RandomString randomString) {
        this.repository = repository;
        this.randomString = randomString;
    }

    public String generate() {
        List<String> generated = repository.findAll().stream()
                .map(VirtualTourAttendance::getTicketId)
                .collect(Collectors.toList());

        String ticket;
        do {
            ticket = generateTicket();
        } while (generated.contains(ticket));

        return ticket;
    }

    private String generateTicket() {
        return randomString.generate(14);
    }

}
