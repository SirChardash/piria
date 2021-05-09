package sirchardash.piria.virtualbank.controllers.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.virtualbank.models.CreditCardInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PaymentRequest {

    private double amount;
    private String referenceNumber;
    private CreditCardInfo payerCard;
    private String inFavorOfAccountNumber;

}