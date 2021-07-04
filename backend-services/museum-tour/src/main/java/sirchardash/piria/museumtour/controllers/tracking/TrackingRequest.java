package sirchardash.piria.museumtour.controllers.tracking;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.models.tracking.TrackingLog;

@Data
@NoArgsConstructor
@AllArgsConstructor
class TrackingRequest {

    @NotNull
    @Valid
    private List<TrackingLog> trackingLogs;

}
