package sirchardash.piria.museumtour.components.geo.restcountries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class RestCountry {

    private String name;
    @JsonProperty("alpha2Code")
    private String countryCode;
    private String region;

}
