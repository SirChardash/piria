package sirchardash.piria.virtualbank.controllers.paymentreport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sirchardash.piria.virtualbank.models.PaymentReport;

@Controller
public class PaymentReportController {

    @GetMapping("/payment-report/{referenceNumber}")
    public ResponseEntity<PaymentReport> paymentReport(@PathVariable String referenceNumber) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
