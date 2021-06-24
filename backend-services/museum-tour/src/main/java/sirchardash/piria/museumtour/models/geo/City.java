package sirchardash.piria.museumtour.models.geo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private String name;
    private String region;
    private String countryCode;

}
