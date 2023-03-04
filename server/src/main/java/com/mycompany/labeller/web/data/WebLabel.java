package com.mycompany.labeller.web.data;

import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class WebLabel extends WebLabelInfo {

    private String classifierData;
    private boolean technical;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public WebLabel(Long id, String name, String description, String classifierData, boolean technical, LocalDateTime createionDate, LocalDateTime updateDate) {
        super(id, name, description);
        this.classifierData = classifierData;
        this.technical = technical;
        this.creationDate = createionDate;
        this.updateDate = updateDate;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setTechnical(boolean technical) {
        this.technical = technical;
    }
   
}
