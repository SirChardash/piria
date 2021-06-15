package sirchardash.piria.museumtour.controllers.weather;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.models.weather.Weather;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private List<Weather> data;

}
