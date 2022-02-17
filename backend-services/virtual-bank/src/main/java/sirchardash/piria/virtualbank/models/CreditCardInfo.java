package sirchardash.piria.virtualbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardInfo {

    private String cardNumber;
    private CreditCardType type;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private String securityCode;

}
