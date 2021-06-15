package sirchardash.piria.museumtour.models.weather.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherResponse {

    private List<Weather> weather;
    @JsonProperty("main")
    private Stats stats;
    @JsonProperty("name")
    private String cityName;

}
