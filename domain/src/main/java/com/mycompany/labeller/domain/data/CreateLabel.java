package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;

/**
 *
 * @author ador
 */
public class CreateLabel extends DomainObject {

    private final LabelName name;
    private final LabelDescription description;
    private final LabelClassifierData classifierData;
    private final LabelTechnical technical;
    private final LabelId parent;

    public CreateLabel(LabelName name, LabelDescription description, 
            LabelClassifierData classifierData, LabelTechnical technical,
            LabelId parent) {
        notNull(name, technical);
        this.name = name;
        this.description = description;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
    }

    public LabelName getName() {
        return name;
    }

    public LabelDescription getDescription() {
        return description;
    }

    public LabelClassifierData getClassifierData() {
        return classifierData;
    }

    public LabelTechnical getTechnical() {
        return technical;
    }

    public LabelId getParent() {
        return parent;
    }

}
