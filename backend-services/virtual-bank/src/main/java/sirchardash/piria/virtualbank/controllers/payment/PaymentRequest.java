package sirchardash.piria.virtualbank.controllers.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.virtualbank.models.CreditCard;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PaymentRequest {

    private double amount;
    private String referenceNumber;
    private CreditCard payerCard;
    private String inFavorOfAccountNumber;

}