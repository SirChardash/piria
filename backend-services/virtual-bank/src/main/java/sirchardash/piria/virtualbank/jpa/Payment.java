package sirchardash.piria.virtualbank.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private int id;
    private double amount;
    private Date time;
    private int cardId;
    private int inFavor;
    private String purpose;
    private String referenceNumber;

}
