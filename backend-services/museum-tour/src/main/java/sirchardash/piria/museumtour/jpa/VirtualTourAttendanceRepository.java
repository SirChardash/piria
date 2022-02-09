package sirchardash.piria.museumtour.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VirtualTourAttendanceRepository extends JpaRepository<VirtualTourAttendance, Integer> {

    List<VirtualTourAttendance> findAllByUserId(String userId);

    Optional<VirtualTourAttendance> findByTourIdAndUserId(int tourId, String ticketId);

}
