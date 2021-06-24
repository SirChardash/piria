package sirchardash.piria.museumtour.components.geo.battuta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class BattutaCity {

    @JsonProperty("city")
    private String name;
    private String region;
    @JsonProperty("country")
    private String countryCode;

}
