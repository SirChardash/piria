package sirchardash.piria.museumtour.controllers.tour;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.jpa.VirtualTour;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourResponse {

    private List<VirtualTour> tours = new ArrayList<>();

}
