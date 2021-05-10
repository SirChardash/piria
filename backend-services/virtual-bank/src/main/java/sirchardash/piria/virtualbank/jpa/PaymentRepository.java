package sirchardash.piria.virtualbank.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByReferenceNumber(String referenceNumber);

}
