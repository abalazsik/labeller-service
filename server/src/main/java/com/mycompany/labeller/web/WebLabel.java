package com.mycompany.labeller.web;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class WebLabel implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String classifierData;
    private boolean technical;
    private LocalDateTime creationDate;

    public WebLabel() {
    }

    public WebLabel(Long id, String name, String description, String classifierData, boolean technical, LocalDateTime createionDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.classifierData = classifierData;
        this.technical = technical;
        this.creationDate = createionDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime createionDate) {
        this.creationDate = createionDate;
    }
    
}
