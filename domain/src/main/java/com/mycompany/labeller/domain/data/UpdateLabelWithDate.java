package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;

/**
 *
 * @author ador
 */
public class UpdateLabelWithDate extends UpdateLabel {

    private LabelUpdateDate updateDate;

    public UpdateLabelWithDate(LabelId id, LabelName name,
            LabelDescription description, LabelClassifierData classifierData,
            LabelTechnical technical, LabelId parent, LabelUpdateDate updateDate) {
        super(id, name, description, classifierData, technical, parent);
        this.updateDate = updateDate;
    }

    public LabelUpdateDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LabelUpdateDate updateDate) {
        this.updateDate = updateDate;
    }

}
