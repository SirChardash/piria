package sirchardash.piria.museumtour.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "virtual_tour_attendance")
public class VirtualTourAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="tour_id")
    private VirtualTour tour;
    private String userId;
    private LocalDateTime timeConfirmed;
    private String ticketId;
    private boolean notified;

}
