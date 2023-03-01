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
public class UpdateLabel {

    private final LabelId id;
    private final LabelDescription description;
    private final LabelName name;
    private final LabelClassifierData classifierData;
    private final LabelTechnical technical;
    private final LabelId parent;

    public UpdateLabel(LabelId id, 
            LabelName name, 
            LabelDescription description, 
            LabelClassifierData classifierData, 
            LabelTechnical technical,
            LabelId parent) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
    }

    public LabelId getId() {
        return id;
    }

    public LabelDescription getDescription() {
        return description;
    }

    public LabelName getName() {
        return name;
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
