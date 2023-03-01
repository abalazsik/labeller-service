package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;

/**
 *
 * @author ador
 */
public class CreateLabelWithDate extends CreateLabel {

    private final LabelCreationDate creationDate;

    public CreateLabelWithDate(LabelName name, LabelDescription description,
            LabelClassifierData classifierData, LabelTechnical technical,
            LabelId parent, LabelCreationDate creationDate) {
        super(name, description, classifierData, technical, parent);
        this.creationDate = creationDate;
    }

    public LabelCreationDate getCreationTime() {
        return creationDate;
    }

}
