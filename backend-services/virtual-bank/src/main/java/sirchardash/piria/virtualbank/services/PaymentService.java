package sirchardash.piria.virtualbank.services;

import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.models.CreditCard;
import sirchardash.piria.virtualbank.models.PaymentStatus;

@Service
public class PaymentService {

    public PaymentStatus pay(CreditCard payerCard,
                             String receivingAccountNumber,
                             double amount,
                             String referenceNumber) {
        return PaymentStatus.OK;
    }

}
