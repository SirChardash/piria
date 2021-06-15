package sirchardash.piria.museumtour.models.weather.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    @JsonProperty("temp")
    private double temperature;
    @JsonProperty("feels_like")
    private double feelsLikeTemperature;
    @JsonProperty("temp_min")
    private double minTemperature;
    @JsonProperty("temp_max")
    private double maxTemperature;
    private int pressure;
    private int humidity;
    private int visibility;

}
