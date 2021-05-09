package sirchardash.piria.virtualbank.services;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.components.CreditCardSearch;
import sirchardash.piria.virtualbank.jpa.Account;
import sirchardash.piria.virtualbank.jpa.AccountRepository;
import sirchardash.piria.virtualbank.jpa.CreditCard;
import sirchardash.piria.virtualbank.jpa.Payment;
import sirchardash.piria.virtualbank.jpa.PaymentRepository;
import sirchardash.piria.virtualbank.models.CreditCardInfo;
import sirchardash.piria.virtualbank.models.PaymentStatus;

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
