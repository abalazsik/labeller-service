
package com.mycompany.labeller.neo4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 *
 * @author ador
 */
@Node("LLABEL")
public class Neo4JLabel implements Serializable {
    
    @Id @GeneratedValue
    private long id;
    
    @Property("name")
    private String name;
    
    @Property("technical")
    private boolean technical;
    
    @Property("description")
    private String description;
    
    @Property("classifier_data")
    private String classifierData;
    
    @Property("creation_date")
    private LocalDateTime creationDate;
    
    @Property("update_date")
    private LocalDateTime updateDate;
    
    @Property("version")
    private long version;
    
    @Relationship(type = "DIRECTED", direction = Relationship.Direction.OUTGOING)
    private Neo4JLabel childOf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTechnical() {
        return technical;
    }

    public void setTechnical(boolean technical) {
        this.technical = technical;
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

    public Neo4JLabel getChildOf() {
        return childOf;
    }

    public void setChildOf(Neo4JLabel childOf) {
        this.childOf = childOf;
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
}
