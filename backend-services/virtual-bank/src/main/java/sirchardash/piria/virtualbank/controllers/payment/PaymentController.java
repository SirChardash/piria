package sirchardash.piria.virtualbank.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sirchardash.piria.virtualbank.models.PaymentStatus;
import sirchardash.piria.virtualbank.services.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest request) {
        PaymentStatus status = service.pay(
                request.getPayerCard(),
                request.getInFavorOfAccountNumber(),
                request.getAmount(),
                request.getReferenceNumber()
        );
        HttpStatus httpStatus = status == PaymentStatus.OK
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(new PaymentResponse(status));
    }


}
