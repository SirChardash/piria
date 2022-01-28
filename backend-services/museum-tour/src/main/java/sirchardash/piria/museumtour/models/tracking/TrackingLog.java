package sirchardash.piria.museumtour.models.tracking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLog {

    @NotNull
    private long timestamp;
    @NotNull
    @Size(max = 500)
    private String userId;
    @Size(max = 500)
    private String category;
    @Size(max = 500)
    private String subcategory;
    @Size(max = 500)
    private String label;
    @Size(max = 500)
    private String value;

}
