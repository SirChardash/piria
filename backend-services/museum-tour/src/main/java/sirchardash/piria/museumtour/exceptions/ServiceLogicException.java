package sirchardash.piria.museumtour.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceLogicException extends RuntimeException {

    private final ServiceError serviceError;
    private final int statusCode;

}
