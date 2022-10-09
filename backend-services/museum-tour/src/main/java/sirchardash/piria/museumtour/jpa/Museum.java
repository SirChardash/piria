package sirchardash.piria.museumtour.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "museum_localized")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Museum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int masterId;
    @NotNull
    private String language;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private String countryCode;
    private String phoneNumber;
    @NotBlank
    private String museumType;
    private String googleLocation;

}
