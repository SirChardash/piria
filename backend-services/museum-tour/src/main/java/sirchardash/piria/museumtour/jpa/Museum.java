package sirchardash.piria.museumtour.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "museum_localized")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Museum {

    @Id
    private Integer id;
    private Integer masterId;
    private String language;
    private String name;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String museumType;
    private String googleLocation;

}
