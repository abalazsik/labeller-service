package com.mycompany.labeller.service.grpc.mapper;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.UpdateLabel;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.data.attributes.NullableLocalDateTimeAttribute;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import com.mycompany.labeller.grcp.CreateLabelRequest;
import com.mycompany.labeller.grcp.UpdateLabelRequest;
import com.mycompany.labeller.grcp.UpdateLabelResponse;
import com.mycompany.labeller.helper.CachedLabelId;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author ador
 */
public class LabellerGRPCMapper {

    public static UpdateLabel toDomain(UpdateLabelRequest request) {
        return new UpdateLabel(
                CachedLabelId.of(request.getId()),
                new LabelName(request.getName()),
                new LabelDescription(request.getDescription()),
                new LabelClassifierData(request.getClassifierData()),
                LabelTechnical.of(request.getTechnical()),
                CachedLabelId.of(request.getParent()),
                new LabelVersion(request.getVersion()));
    }

    public static CreateLabel toDomain(CreateLabelRequest request) {
        return new CreateLabel(
                new LabelName(request.getName()),
                new LabelDescription(request.getDescription()),
                new LabelClassifierData(request.getClassifierData()),
                LabelTechnical.of(request.getTechnical()),
                CachedLabelId.of(request.getParent()));
    }

    public static UpdateLabelResponse toGRPC(LabelVersion version) {
        return UpdateLabelResponse.newBuilder().setVersion(version.getValue()).build();
    }

    public static com.mycompany.labeller.grcp.Label toGRPC(Label label) {
        return com.mycompany.labeller.grcp.Label
                .newBuilder()
                .setId(label.getId().getValue())
                .setName(label.getName().getValue())
                .setDescription(NullableStringAttribute.getValue(label.getDescription()))
                .setClassifierData(NullableStringAttribute.getValue(label.getClassifierData()))
                .setTechnical(label.getTechnical().value())
                .setParent(asLong(label.getParent()))
                .setCreationDate(asInt(label.getCreationDate()))
                .setUpdateDate(asInt(label.getUpdateDate())).build();
    }

    private static int asInt(NullableLocalDateTimeAttribute dta) {
        LocalDateTime value = NullableLocalDateTimeAttribute.getValue(dta);

        return value != null ? (int) value.toEpochSecond(ZoneOffset.UTC) : 0;
    }

    private static long asLong(LabelId id) {
        return id == null ? 0 : id.getValue();
    }

}
