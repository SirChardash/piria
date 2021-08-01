package sirchardash.piria.museumtour.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationResponse {

    public static final ConfirmationResponse SUCCESS = new ConfirmationResponse(Confirmation.SUCCESS);

    private Confirmation status;

}
