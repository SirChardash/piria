package sirchardash.piria.museumtour.controllers.museum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.jpa.Museum;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMuseumRequest {

    @JsonProperty("localizations")
    @Valid
    @NotNull
    private List<Museum> museumLocalizations;

}
