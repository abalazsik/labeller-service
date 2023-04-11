package com.mycompany.labeller.grpc;

import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.service.grpc.port.LabellerGRCPServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class LabellerGRPCService {

    private Server server;
    private LabelService labelService;
    private int port;

    @Autowired
    public LabellerGRPCService(@Value("${labeller.grpc.port:10002}") String port, LabelService labelService) {
        this.labelService = labelService;
        this.port = Integer.parseInt(port);
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new LabellerGRCPServer(labelService))
                .build()
                .start();
    }

    @PreDestroy
    public void preDestroy() throws InterruptedException {
        server.shutdown().awaitTermination(1L, TimeUnit.SECONDS);
    }
}
