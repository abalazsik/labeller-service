package com.mycompany.labeller.web;

/**
 *
 * @author ador
 */
public class WebUpdateLabel {

    private String description;
    private String name;
    private String classifierData;
    private boolean technical;
    private Long parent;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
    
}
