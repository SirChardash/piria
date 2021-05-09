package sirchardash.piria.virtualbank.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirchardash.piria.virtualbank.jpa.CreditCard;
import sirchardash.piria.virtualbank.jpa.CreditCardRepository;
import sirchardash.piria.virtualbank.models.CreditCardInfo;

@Component
public class CreditCardSearch {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardCheck creditCardCheck;

    @Autowired
    public CreditCardSearch(CreditCardRepository creditCardRepository,
                            CreditCardCheck creditCardCheck) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardCheck = creditCardCheck;
    }

    public CreditCard findAccountFor(CreditCardInfo creditCardInfo) {
        return creditCardRepository.findAllByFirstNameAndLastName(
                creditCardInfo.getFirstName(),
                creditCardInfo.getLastName()
        ).stream()
                .filter(stored -> creditCardCheck.isValid(stored, creditCardInfo))
                .findFirst().orElse(null);
    }
}
