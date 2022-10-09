package sirchardash.piria.museumtour.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "virtual_tour")
public class VirtualTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer museumId;
    private String title;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;
    private double ticketPrice;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "virtualTour")
    private List<TourContent> content;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<VirtualTourAttendance> attendances;

}
