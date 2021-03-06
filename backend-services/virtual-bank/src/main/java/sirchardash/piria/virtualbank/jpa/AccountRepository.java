package sirchardash.piria.virtualbank.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNumber(String accountNumber);

}
