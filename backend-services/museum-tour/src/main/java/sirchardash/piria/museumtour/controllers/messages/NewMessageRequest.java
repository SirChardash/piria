package sirchardash.piria.museumtour.controllers.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageRequest {

    @NotBlank
    private String message;

}
