package com.mycompany.labeller.h2;

import com.mycompany.labeller.domain.data.attributes.LabelId;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author ador
 */
@Table(name = "LLABEL")
public class H2Label implements Serializable {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CLASSIFIER_DATA = "classifier_data";
    public static final String TECHNICAL = "technical";
    public static final String PARENT = "parent";
    public static final String CREATION_DATE = "creation_date";
    public static final String UPDATE_DATE = "update_date";

    @Id
    private LabelId id;

    @Column(NAME)
    private String name;

    @Column(DESCRIPTION)
    private String description;

    @Column(CLASSIFIER_DATA)
    private String classifierData;

    @Column(TECHNICAL)
    private boolean technical;

    @Column(PARENT)
    private LabelId parent;

    @Column(CREATION_DATE)
    private LocalDateTime creationDate;
    
    @Column(UPDATE_DATE)
    private LocalDateTime updateDate;

    public H2Label() {
    }

    public H2Label(LabelId id, 
            String name, 
            String description, 
            String classifierData, 
            boolean technical, 
            LabelId parent, 
            LocalDateTime creationDate,
            LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public LabelId getId() {
        return id;
    }

    public void setId(LabelId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassifierData() {
        return classifierData;
    }

    public void setClassifierData(String classifierData) {
        this.classifierData = classifierData;
    }

    public boolean isTechnical() {
        return technical;
    }

    public void setTechnical(boolean technical) {
        this.technical = technical;
    }

    public LabelId getParent() {
        return parent;
    }

    public void setParent(LabelId parent) {
        this.parent = parent;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    
}
