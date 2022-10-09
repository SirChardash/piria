package sirchardash.piria.museumtour.components.geo.restcountries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class RestCountry {

    private Name name;
    @JsonProperty("cca2")
    private String countryCode;
    private String region;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name {

        private String common;

    }

}
