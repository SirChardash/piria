package sirchardash.piria.virtualbank.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.jpa.Payment;
import sirchardash.piria.virtualbank.jpa.PaymentRepository;

@Service
public class PaymentReportService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentReportService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> getPaymentReport(String referenceNumber,
                                          String authToken) {
        return repository.findAllByReferenceNumber(referenceNumber);
    }

}
