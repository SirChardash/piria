package sirchardash.piria.virtualbank.controllers.paymentreport;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportServiceGrpc.PaymentReportServiceImplBase;
import sirchardash.piria.virtualbank.services.PaymentReportService;

@Controller
public class PaymentReportGrpc extends PaymentReportServiceImplBase {

    public final PaymentReportService service;

    @Autowired
    public PaymentReportGrpc(PaymentReportService service) {
        this.service = service;
    }

    @Override
    public void getPayment(PaymentReportRequest request, StreamObserver<PaymentReportResponse> responseObserver) {
        List<Payment> reports = service.getPaymentReport(
                request.getReferenceNumber(),
                request.getAuthToken()
        ).stream()
                .map(payment -> Payment.newBuilder()
                        .setAmount(payment.getAmount())
                        .setTime(Timestamp.newBuilder().setSeconds(payment.getTime().getTime() / 1000).build())
                        .build()
                ).collect(Collectors.toList());

        PaymentReportResponse response = PaymentReportResponse.newBuilder()
                .addAllReports(reports)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
