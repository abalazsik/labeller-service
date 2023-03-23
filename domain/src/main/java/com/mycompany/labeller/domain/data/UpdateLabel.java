package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import org.jmolecules.ddd.annotation.Identity;

/**
 *
 * @author ador
 */
public class UpdateLabel extends DomainObject {

    @Identity
    private final LabelId id;
    private final LabelDescription description;
    private final LabelName name;
    private final LabelClassifierData classifierData;
    private final LabelTechnical technical;
    private final LabelId parent;
    private final LabelVersion version;

    public UpdateLabel(LabelId id,
            LabelName name,
            LabelDescription description,
            LabelClassifierData classifierData,
            LabelTechnical technical,
            LabelId parent,
            LabelVersion version) {
        notNull(id, name, technical, version);
        this.id = id;
        this.description = description;
        this.name = name;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
        this.version = version;
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

    public LabelVersion getVersion() {
        return version;
    }

}
