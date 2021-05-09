package sirchardash.piria.virtualbank.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    private int id;
    private int accountId;
    private String cardType;
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private String securityCode;

}
