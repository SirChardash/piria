package sirchardash.piria.museumtour.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "(EN)|(SR)")
    private String language;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private String phoneNumber;
    @NotBlank
    private String museumType;
    @Pattern(regexp = "^https://www[.]google[.]com/maps/embed[?]pb=.+$")
    private String googleLocation;

}
