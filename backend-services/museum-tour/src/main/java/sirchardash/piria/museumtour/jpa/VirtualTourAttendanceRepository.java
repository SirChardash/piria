package sirchardash.piria.museumtour.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualTourAttendanceRepository extends JpaRepository<VirtualTourAttendance, Integer> {

    List<VirtualTourAttendance> findAllByUserId(String userId);

}
