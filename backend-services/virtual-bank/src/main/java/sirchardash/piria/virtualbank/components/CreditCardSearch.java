package sirchardash.piria.virtualbank.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirchardash.piria.virtualbank.jpa.CreditCard;
import sirchardash.piria.virtualbank.jpa.CreditCardRepository;
import sirchardash.piria.virtualbank.models.CreditCardInfo;

@Component
public class CreditCardSearch {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardInfoCheck creditCardInfoCheck;

    @Autowired
    public CreditCardSearch(CreditCardRepository creditCardRepository,
                            CreditCardInfoCheck creditCardInfoCheck) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardInfoCheck = creditCardInfoCheck;
    }

    public CreditCard findAccountFor(CreditCardInfo creditCardInfo) {
        return creditCardRepository.findAllByFirstNameAndLastName(
                creditCardInfo.getFirstName(),
                creditCardInfo.getLastName()
        ).stream()
                .filter(stored -> creditCardInfoCheck.isValid(stored, creditCardInfo))
                .findFirst().orElse(null);
    }
}
