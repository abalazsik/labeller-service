package com.mycompany.labeller.web.mapper;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.GetLabelsForString;
import com.mycompany.labeller.domain.data.attributes.GetLabelsForStringText;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.NullableLocalDateTimeAttribute;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import com.mycompany.labeller.helper.CachedLabelId;
import com.mycompany.labeller.web.data.WebCreateLabel;
import com.mycompany.labeller.web.data.WebGetLabelsForString;
import com.mycompany.labeller.web.data.WebLabel;
import com.mycompany.labeller.web.data.WebLabelInfo;

/**
 *
 * @author ador
 */
public class WebLabelMapper {

    public static WebLabelInfo toInfo(Label label) {

        return new WebLabelInfo(
                label.getId().getValue(),
                label.getName().getValue(),
                NullableStringAttribute.getValue(label.getDescription()));
    }

    public static WebLabel fromDomain(Label label) {
        return new WebLabel(label.getId().getValue(),
                label.getName().getValue(),
                NullableStringAttribute.getValue(label.getDescription()),
                NullableStringAttribute.getValue(label.getClassifierData()),
                label.getTechnical().value(),
                NullableLocalDateTimeAttribute.getValue(label.getCreationDate()),
                NullableLocalDateTimeAttribute.getValue(label.getUpdateDate())
        );
    }

    public static CreateLabel toDomainCreate(WebCreateLabel webCreateLabel) {
        return new CreateLabel(
                new LabelName(webCreateLabel.getName()),
                new LabelDescription(webCreateLabel.getDescription()),
                new LabelClassifierData(webCreateLabel.getClassifierData()),
                LabelTechnical.of(webCreateLabel.isTechnical()),
                CachedLabelId.of(webCreateLabel.getParent())
        );
    }

    public static GetLabelsForString toDomainGetLabelsForString(WebGetLabelsForString text) {
        return new GetLabelsForString(new GetLabelsForStringText(text.getValue()));
    }
}
