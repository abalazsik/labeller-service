
package com.mycompany.labeller.javafx.client;

import com.mycompany.labeller.grcp.LabellerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author ador
 */
public class LabellerService {

    private LabellerServiceGrpc.LabellerServiceBlockingStub service;
    
    private LabellerService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 10002).usePlaintext().build();
        service = LabellerServiceGrpc.newBlockingStub(channel);
    }
    
}
