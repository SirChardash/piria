package sirchardash.piria.virtualbank.controllers.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import sirchardash.piria.virtualbank.models.PaymentStatus;

@Data
@AllArgsConstructor
class PaymentResponse {

    private PaymentStatus status;

}