package sirchardash.piria.virtualbank.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    List<CreditCard> findAllByFirstNameAndLastName(String firstName, String lastName);

}
