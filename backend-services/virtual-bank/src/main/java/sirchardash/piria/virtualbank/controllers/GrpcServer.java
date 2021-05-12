package sirchardash.piria.virtualbank.controllers;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import sirchardash.piria.virtualbank.controllers.paymentreport.PaymentReportGrpc;

@Slf4j
@Service
public class GrpcServer {

    private final int port;
    private final PaymentReportGrpc paymentReport;

    @Autowired
    public GrpcServer(@Value("${grpc.port}") int port,
                      PaymentReportGrpc paymentReport) {
        this.port = port;
        this.paymentReport = paymentReport;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeServer() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port)
                .addService(paymentReport)
                .build();

        server.start();
        log.info("gRPC server initialized with port: {}", port);
        server.awaitTermination();
    }

}
