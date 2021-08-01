package sirchardash.piria.museumtour.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ServiceError {

    /**
     * Describes a situation when a duplicate entry is attempted where uniqueness needs to be fulfilled.
     */
    DUPLICATE_ENTRIES("1"),
    /**
     * Describes a situation where non-supported locale is asked for.
     */
    UNSUPPORTED_LANGUAGE("2"),
    /**
     * Describes a generic-like situation where related data is requested, but doesn't exist. Example is logging a
     * message for user that doesn't exist, but not asking for user that doesn't exist.
     */
    MODEL_REFERENCE_FAILS("3");

    @Getter
    private final String code;

}
