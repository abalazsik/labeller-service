package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import java.util.Objects;

/**
 *
 * @author ador
 */
public class Label extends DomainObject {

    private final LabelId id;
    private final LabelName name;
    private final LabelDescription description;
    private final LabelClassifierData classifierData;
    private final LabelTechnical technical;
    private final LabelId parent;
    private final LabelCreationDate creationDate;
    private final LabelUpdateDate updateDate;
    private final LabelVersion version;

    public Label(LabelId id,
            LabelName name,
            LabelDescription description,
            LabelClassifierData classifierData,
            LabelTechnical technical,
            LabelId parent,
            LabelCreationDate creationDate,
            LabelUpdateDate updateDate,
            LabelVersion version
    ) {
        notNull(id, name, technical, version);
        this.id = id;
        this.name = name;
        this.description = description;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.version = version;
    }

    public LabelId getId() {
        return id;
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

    public LabelCreationDate getCreationDate() {
        return creationDate;
    }

    public LabelUpdateDate getUpdateDate() {
        return updateDate;
    }

    public LabelVersion getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Objects.hashCode(this.classifierData);
        hash = 31 * hash + Objects.hashCode(this.technical);
        hash = 31 * hash + Objects.hashCode(this.parent);
        hash = 31 * hash + Objects.hashCode(this.version);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Label other = (Label) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.classifierData, other.classifierData)) {
            return false;
        }
        if (!Objects.equals(this.technical, other.technical)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        return Objects.equals(this.parent, other.parent);
    }

    @Override
    public String toString() {
        return "Label{" 
                + "id=" + id 
                + ", name=" + name
                + ", description=" + description 
                + ", classifierData=" + classifierData 
                + ", technical=" + technical 
                + ", parent=" + parent 
                + ", creationDate=" + creationDate 
                + ", version=" + version
                + '}';
    }

}
