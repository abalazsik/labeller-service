
package com.mycompany.labeller.javafx.client.model;

import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ador
 */
public class JFXLabel {
    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;
    private final SimpleStringProperty classifierData;
    private final SimpleBooleanProperty technical;

    public JFXLabel(
            Long id, 
            String name, 
            String description, 
            String classifierData, 
            Boolean technical) {
        
        this.id = new ReadOnlyLongWrapper(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(name);
        this.classifierData = new SimpleStringProperty(name);
        this.technical = new SimpleBooleanProperty(technical);
    }
    
    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getClassifierData() {
        return classifierData.get();
    }

    public Boolean getTechnical() {
        return technical.get();
    }

}
