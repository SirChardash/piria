package sirchardash.piria.virtualbank.components;

import org.springframework.stereotype.Component;
import sirchardash.piria.virtualbank.jpa.CreditCard;
import sirchardash.piria.virtualbank.models.CreditCardInfo;

@Component
public class CreditCardCheck {

    public boolean isValid(CreditCard stored, CreditCardInfo submitted) {
        return stored.getCardNumber().equals(submitted.getCardNumber())
                && stored.getCardType().equals(submitted.getType())
                && stored.getExpirationDate().equals(submitted.getExpirationDate())
                && stored.getSecurityCode().equals(submitted.getSecurityCode());
    }

}
