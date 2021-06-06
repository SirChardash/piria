package sirchardash.piria.museumtour.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportRequest;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportResponse;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportServiceGrpc;

@Service
public class MehService {

//    @PostConstruct
    public void eh() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        PaymentReportServiceGrpc.PaymentReportServiceBlockingStub stub
                = PaymentReportServiceGrpc.newBlockingStub(channel);

        PaymentReportResponse response = stub.getPayment(PaymentReportRequest.newBuilder()
                .setReferenceNumber("1337420699")
                .setAuthToken("oh")
                .build());

        System.out.println("Response received from server:\n" + response);

        channel.shutdown();
    }
}
