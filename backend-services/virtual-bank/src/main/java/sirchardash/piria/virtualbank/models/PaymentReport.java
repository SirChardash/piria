package sirchardash.piria.virtualbank.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReport {

    private String referenceNumber;
    private double amount;
    private String payer;
    private String inFavor;
    private Date date;

}
