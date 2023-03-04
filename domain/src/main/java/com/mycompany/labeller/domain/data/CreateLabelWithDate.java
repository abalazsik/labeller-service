package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;

/**
 *
 * @author ador
 */
public class CreateLabelWithDate extends CreateLabel {

    private final LabelCreationDate creationDate;
    private final LabelVersion version;

    public CreateLabelWithDate(LabelName name, LabelDescription description,
            LabelClassifierData classifierData, LabelTechnical technical,
            LabelId parent, LabelCreationDate creationDate, LabelVersion version) {
        super(name, description, classifierData, technical, parent);
        notNull(creationDate, version);
        this.creationDate = creationDate;
        this.version = version;
    }

    public LabelCreationDate getCreationTime() {
        return creationDate;
    }

    public LabelVersion getVersion() {
        return version;
    }

}
