package sirchardash.piria.virtualbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private String cardNumber;
    private Type type;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private String securityCode;

    public enum Type {
        VISA,
        MASTERCARD,
        AMERICAN_EXPRESS
    }

}
