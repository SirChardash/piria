package sirchardash.piria.museumtour.components.payment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportRequest;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportResponse;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportServiceGrpc;

import javax.annotation.PreDestroy;

@Component
public class PaymentReportService {

    private final ManagedChannel channel;

    PaymentReportService() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8083)
                .usePlaintext()
                .build();
    }

    public PaymentReportResponse get(String referenceNumber) {
        PaymentReportServiceGrpc.PaymentReportServiceBlockingStub stub
                = PaymentReportServiceGrpc.newBlockingStub(channel);

        return stub.getPayment(PaymentReportRequest.newBuilder()
                .setReferenceNumber(referenceNumber)
                .setAuthToken("oh")
                .build());
    }

    @PreDestroy
    void shutdown() {
        channel.shutdown();
    }

}
