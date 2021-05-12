package sirchardash.piria.virtualbank.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.virtualbank.models.CreditCardType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private CreditCardType cardType;
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private String securityCodeHash;

}
