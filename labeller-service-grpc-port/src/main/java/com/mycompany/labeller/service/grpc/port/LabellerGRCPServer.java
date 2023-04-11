package com.mycompany.labeller.service.grpc.port;

import com.mycompany.labeller.commons.CachedLabelId;
import com.mycompany.labeller.commons.security.SecurityUtil;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.GetLabelsForString;
import com.mycompany.labeller.domain.data.attributes.GetLabelsForStringText;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelRangeFrom;
import com.mycompany.labeller.domain.data.attributes.LabelRangeLimit;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.grcp.CreateLabelRequest;
import com.mycompany.labeller.grcp.CreateLabelResponse;
import com.mycompany.labeller.grcp.DeleteLabelRequest;
import com.mycompany.labeller.grcp.DeleteLabelResponse;
import com.mycompany.labeller.grcp.GetAllLabelsRequest;
import com.mycompany.labeller.grcp.GetAllLabelsResponse;
import com.mycompany.labeller.grcp.GetLabelByIdRequest;
import com.mycompany.labeller.grcp.GetLabelByIdResponse;
import com.mycompany.labeller.grcp.GetLabelsForStringRequest;
import com.mycompany.labeller.grcp.GetLabelsForStringResponse;
import com.mycompany.labeller.grcp.LabellerServiceGrpc;
import com.mycompany.labeller.grcp.UpdateLabelRequest;
import com.mycompany.labeller.grcp.UpdateLabelResponse;
import com.mycompany.labeller.service.grpc.mapper.LabellerGRPCMapper;
import io.grpc.stub.StreamObserver;
import java.util.Optional;

/**
 *
 * @author ador
 */
public class LabellerGRCPServer extends LabellerServiceGrpc.LabellerServiceImplBase {

    private final LabelService labelService;

    public LabellerGRCPServer(LabelService labelService) {
        this.labelService = labelService;
    }

    @Override
    public void updateLabel(UpdateLabelRequest request, StreamObserver<UpdateLabelResponse> responseObserver) {
        try {
            LabelVersion version = labelService.update(LabellerGRPCMapper.toDomain(request), SecurityUtil.getUser());
            responseObserver.onNext(LabellerGRPCMapper.toGRPC(version));
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getLabelById(GetLabelByIdRequest request, StreamObserver<GetLabelByIdResponse> responseObserver) {
        LabelId id = CachedLabelId.of(request.getId());

        Optional<Label> label = labelService.getById(id, SecurityUtil.getUser());

        if (label.isPresent()) {
            responseObserver.onNext(
                    GetLabelByIdResponse.newBuilder()
                            .setResult(LabellerGRPCMapper.toGRPC(label.get())).build());
        } else {
            responseObserver.onError(new RuntimeException(String.format("Label not found with id %d", request.getId())));// TODO: OMG what now?
        }
    }

    @Override
    public void deleteLabel(DeleteLabelRequest request, StreamObserver<DeleteLabelResponse> responseObserver) {
        try {
            labelService.delete(CachedLabelId.of(request.getId()), SecurityUtil.getUser());
            responseObserver.onNext(DeleteLabelResponse.getDefaultInstance());
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void createLabel(CreateLabelRequest request, StreamObserver<CreateLabelResponse> responseObserver) {
        try {
            LabelId id = labelService.create(LabellerGRPCMapper.toDomain(request), SecurityUtil.getUser());
            responseObserver.onNext(CreateLabelResponse.newBuilder().setId(id.getValue()).build());
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getLabelsForString(GetLabelsForStringRequest request, StreamObserver<GetLabelsForStringResponse> responseObserver) {
        try {
            GetLabelsForStringResponse.Builder builder = GetLabelsForStringResponse.newBuilder();
            labelService.getLabelsForString(new GetLabelsForString(new GetLabelsForStringText(request.getText())), SecurityUtil.getUser()).forEach(label -> {
                builder.addLabels(LabellerGRPCMapper.toGRPC(label));
            });
            responseObserver.onNext(builder.build());
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAll(GetAllLabelsRequest request, StreamObserver<GetAllLabelsResponse> responseObserver) {
        LabelRange range = new LabelRange(new LabelRangeFrom(request.getFrom()), new LabelRangeLimit(request.getLimit()));

        try {
            GetAllLabelsResponse.Builder builder = GetAllLabelsResponse.newBuilder();
            labelService.getAll(range, SecurityUtil.getUser()).forEach(label -> {
                builder.addLabels(LabellerGRPCMapper.toGRPC(label));
            });
            responseObserver.onNext(builder.build());
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

}
