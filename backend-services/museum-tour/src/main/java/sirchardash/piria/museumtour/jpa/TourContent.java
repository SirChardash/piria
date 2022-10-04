package sirchardash.piria.museumtour.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour_content")
public class TourContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] content;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "virtual_tour_id", nullable = false)
    private VirtualTour virtualTour;

    public enum Type {
        IMAGE,
        VIDEO,
        LINK
    }

}
