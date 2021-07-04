package sirchardash.piria.museumtour.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tracking_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLog {

    @Id
    private Integer id;
    private java.sql.Timestamp time;
    private String category;
    private String subcategory;
    private String label;
    private String value;

}
