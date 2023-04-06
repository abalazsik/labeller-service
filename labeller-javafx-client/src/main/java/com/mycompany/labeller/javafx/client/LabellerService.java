package com.mycompany.labeller.javafx.client;

import com.mycompany.labeller.grcp.GetAllLabelsRequest;
import com.mycompany.labeller.grcp.LabellerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author ador
 */
public class LabellerService {

    private LabellerServiceGrpc.LabellerServiceBlockingStub service;

    private LabellerService(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        service = LabellerServiceGrpc.newBlockingStub(channel);
    }

    public void getAll(int from, int limit) {
        service.getAll(GetAllLabelsRequest.newBuilder().setFrom(from).setLimit(limit).build());
    }

}
