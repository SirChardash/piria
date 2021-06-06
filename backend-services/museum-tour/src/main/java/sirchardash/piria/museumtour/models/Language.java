package sirchardash.piria.museumtour.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Language {

    ENGLISH("en"),
    SERBIAN("sr");

    private final String code;

    public static Language forCode(String code) {
        return SERBIAN.code.equals(code) ? SERBIAN : ENGLISH;
    }

}
