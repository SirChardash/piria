package sirchardash.piria.museumtour.models.tracking;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLog {

    @NotNull
    private long timestamp;
    @Size(max = 500)
    private String username;
    @Size(max = 500)
    private String category;
    @Size(max = 500)
    private String subcategory;
    @Size(max = 500)
    private String label;
    @Size(max = 500)
    private String value;

}
