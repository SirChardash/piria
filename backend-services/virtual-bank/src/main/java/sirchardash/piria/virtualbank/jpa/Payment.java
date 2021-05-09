package sirchardash.piria.virtualbank.jpa;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String referenceNumber;

}
