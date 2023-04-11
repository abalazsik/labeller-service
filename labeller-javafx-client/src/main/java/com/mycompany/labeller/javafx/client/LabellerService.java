package com.mycompany.labeller.javafx.client;

import com.mycompany.labeller.grcp.GetAllLabelsRequest;
import com.mycompany.labeller.grcp.GetAllLabelsResponse;
import com.mycompany.labeller.grcp.LabellerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author ador
 */
public class LabellerService {

    private ManagedChannel channel;
    private LabellerServiceGrpc.LabellerServiceBlockingStub service;
    private String username;
    private String password;

    private LabellerService(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        service = LabellerServiceGrpc.newBlockingStub(channel);
    }

    public void setCredentials(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public boolean isCredentialsSet() {
        return username != null && password != null;
    }

    public GetAllLabelsResponse getAll(int from, int limit) {
        try {
            return service.getAll(GetAllLabelsRequest.newBuilder().setFrom(from).setLimit(limit).build());
        } catch (Exception e) {
            /*if () {
                setCredentials(null, null);
            }*/

            throw e;
        }
    }
    
    public static LabellerService INSTANCE;

    public static void init(String host, int port) {
        INSTANCE = new LabellerService(host, port);
    }
    
    public static void close() {
        if (INSTANCE != null) {
            INSTANCE.channel.shutdown();
            INSTANCE = null;
        }
    }

}
