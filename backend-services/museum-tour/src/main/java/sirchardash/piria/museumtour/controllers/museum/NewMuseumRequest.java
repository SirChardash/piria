package sirchardash.piria.museumtour.controllers.museum;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.jpa.Museum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMuseumRequest {

    @JsonProperty("localizations")
    private List<Museum> museumLocalizations;

}
