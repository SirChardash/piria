package sirchardash.piria.museumtour.models.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private String cityName;
    private String description;
    private String iconUrl;
    private String temperature;
    private String feelsLikeTemperature;

}
