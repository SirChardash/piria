package sirchardash.piria.museumtour.controllers.museum;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.jpa.Museum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuseumResponse {

    private List<Museum> museums;

}
