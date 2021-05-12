package sirchardash.piria.virtualbank.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirchardash.piria.virtualbank.jpa.CreditCard;
import sirchardash.piria.virtualbank.models.CreditCardInfo;

@Component
public class CreditCardInfoCheck {

    private final MessageDigest messageDigest;

    @Autowired
    public CreditCardInfoCheck() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public boolean isValid(CreditCard stored, CreditCardInfo submitted) {

        return stored.getCardNumber().equals(submitted.getCardNumber())
                && stored.getCardType().equals(submitted.getType())
                && stored.getExpirationDate().equals(submitted.getExpirationDate())
                && equalsHash(stored.getSecurityCodeHash(), submitted.getSecurityCode());
    }

    private boolean equalsHash(String hashed, String plain) {
        return hashed.equals(Base64.getEncoder().encodeToString(messageDigest.digest(plain.getBytes())));
    }

}
