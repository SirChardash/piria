package sirchardash.piria.museumtour.models.weather.openweather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private int id;
    private String name;
    private String country;
    private String temperature;
    private String feelsLikeTemperature;

}
