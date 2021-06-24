package sirchardash.piria.museumtour.components.geo.battuta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class BattutaRegion {

    @JsonProperty("region")
    private String name;
    @JsonProperty("country")
    private String countryCode;

}
