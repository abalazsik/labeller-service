package com.mycompany.labeller.web.data;

/**
 *
 * @author ador
 */
public class WebCreateLabel {

    private String name;
    private String description;
    private String classifierData;
    private boolean technical;
    private Long parent;

    public WebCreateLabel() {
    }

    public WebCreateLabel(String name, String description, String classifierData, boolean technical, Long parent) {
        this.name = name;
        this.description = description;
        this.classifierData = classifierData;
        this.technical = technical;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getClassifierData() {
        return classifierData;
    }

    public boolean isTechnical() {
        return technical;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassifierData(String classifierData) {
        this.classifierData = classifierData;
    }

    public void setTechnical(boolean technical) {
        this.technical = technical;
    }

    public Long getParent() {
        return parent;
    }
    
}
