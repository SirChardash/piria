package sirchardash.piria.virtualbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.components.CreditCardSearch;
import sirchardash.piria.virtualbank.jpa.*;
import sirchardash.piria.virtualbank.models.CreditCardInfo;
import sirchardash.piria.virtualbank.models.PaymentStatus;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PaymentService {

    private final CreditCardSearch creditCardSearch;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(CreditCardSearch creditCardSearch,
                          AccountRepository accountRepository,
                          PaymentRepository paymentRepository) {
        this.creditCardSearch = creditCardSearch;
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentStatus pay(CreditCardInfo payerCard,
                             String receivingAccountNumber,
                             double amount,
                             String purpose,
                             String referenceNumber) {
        CreditCard payer = creditCardSearch.findAccountFor(payerCard);
        Account receiver = accountRepository.findByAccountNumber(receivingAccountNumber);

        if (payer == null) {
            return PaymentStatus.BAD_CARD_INFO;
        } else if (payer.getAccount().getBalance() < amount) {
            return PaymentStatus.NOT_ENOUGH_MONEY;
        } else if (receiver == null) {
            return PaymentStatus.BAD_IN_FAVOR_INFO;
        }

        Payment payment = new Payment(
                0,
                amount,
                new Date(),
                payer.getId(),
                receiver.getId(),
                purpose,
                referenceNumber
        );

        payer.getAccount().setBalance(payer.getAccount().getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        paymentRepository.save(payment);
        accountRepository.save(payer.getAccount());
        accountRepository.save(receiver);

        return PaymentStatus.OK;
    }

}
