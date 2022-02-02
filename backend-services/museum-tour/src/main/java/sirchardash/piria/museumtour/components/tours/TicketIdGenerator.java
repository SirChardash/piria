package sirchardash.piria.museumtour.components.tours;

import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class TicketIdGenerator {

    private final VirtualTourAttendanceRepository repository;

    public TicketIdGenerator(VirtualTourAttendanceRepository repository) {
        this.repository = repository;
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
        int leftLimit = 48; // numeral '0'
        int rightLimit = 96; // letter 'Z'
        int targetStringLength = 14;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
