package sirchardash.piria.museumtour.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {

    public static final Confirmation SUCCESS = new Confirmation(true, null);

    private boolean success;
    private String message;

}
