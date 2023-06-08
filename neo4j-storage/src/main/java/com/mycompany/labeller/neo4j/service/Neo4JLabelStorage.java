package com.mycompany.labeller.neo4j.service;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import com.mycompany.labeller.domain.repository.LabelRepository;
import com.mycompany.labeller.helper.CachedLabelId;
import com.mycompany.labeller.neo4j.Neo4JLabel;
import com.mycompany.labeller.neo4j.repository.Neo4JLabelRepository;
import java.util.Optional;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class Neo4JLabelStorage implements LabelRepository {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4JLabelStorage.class);
    
    @Autowired
    private Neo4JLabelRepository repository;
    
    @Override
    public Stream<Label> getAll(LabelRange range) {
         LOGGER.trace("Executing getAll");
        int from = range.getFrom().getValue();
        int limit = range.getLimit().getValue();
        return repository.findAll(PageRequest.of(from / limit, limit)).stream().map(this::toDomain);
    }
    
    @Override
    public Stream<Label> getClassifiableLabels() {
        LOGGER.trace("Executing getClassifiableLabels");
        return repository.getByClassifiable().map(this::toDomain);
    }
    
    @Override
    public LabelId create(CreateLabelWithDate createLabel) {
        LOGGER.trace("Executing create");
        Neo4JLabel n4l = new Neo4JLabel();
        
        n4l.setName(createLabel.getName().getValue());
        n4l.setDescription(NullableStringAttribute.getValue(createLabel.getDescription()));
        n4l.setClassifierData(NullableStringAttribute.getValue(createLabel.getClassifierData()));
        n4l.setTechnical(createLabel.getTechnical().value());
        n4l.setCreationDate(LabelCreationDate.getValue(createLabel.getCreationTime()));
        n4l.setUpdateDate(LabelUpdateDate.getValue(createLabel.getCreationTime()));
        n4l.setVersion(createLabel.getVersion().getValue());
        
        if (createLabel.getParent() != null) {
            Optional<Neo4JLabel> optional = repository.findById(createLabel.getParent().getValue());
            n4l.setChildOf(optional.get());
        }
        
        n4l = repository.save(n4l);
        
        return CachedLabelId.of(n4l.getId());
    }
    
    @Override
    public void delete(LabelId id) {
        LOGGER.trace("Executing delete");
        repository.deleteById(id.getValue());
    }
    
    @Override
    public void unlink(LabelId id) {
        LOGGER.trace("Executing unlink");
        repository.unlink(id.getValue());
    }
    
    @Override
    public Optional<Label> getById(LabelId id) {
        LOGGER.trace("Executing getById");
        return repository.findById(id.getValue()).map(this::toDomain);
    }
    
    @Override
    public Optional<Label> getByName(LabelName name) {
        LOGGER.trace("Executing getByName");
        return repository.getByName(name.getValue()).map(this::toDomain);
    }
    
    @Override
    public LabelVersion update(UpdateLabelWithDate update) {
        LOGGER.trace("Executing update");
        Neo4JLabel n4l = repository.findById(update.getId().getValue()).get();
        
        n4l.setDescription(NullableStringAttribute.getValue(update.getDescription()));
        n4l.setClassifierData(NullableStringAttribute.getValue(update.getClassifierData()));
        n4l.setName(update.getName().getValue());
        n4l.setTechnical(update.getTechnical().value());
        n4l.setVersion(update.getVersion().getValue());
        
        if (update.getParent() != null) {
            Optional<Neo4JLabel> optional = repository.findById(update.getParent().getValue());
            n4l.setChildOf(optional.get());
        }
        
        repository.save(n4l);
        
        return update.getVersion();
    }
    
    private Label toDomain(Neo4JLabel n4jl) {
        LabelId parent = null;
        
        if (n4jl.getChildOf() != null) {
            parent = CachedLabelId.of(n4jl.getChildOf().getId());
        }
        
        return new Label(
                CachedLabelId.of(n4jl.getId()),
                new LabelName(n4jl.getName()),
                new LabelDescription(n4jl.getDescription()),
                new LabelClassifierData(n4jl.getDescription()),
                LabelTechnical.of(n4jl.isTechnical()),
                parent,
                new LabelCreationDate(n4jl.getCreationDate()),
                new LabelUpdateDate(n4jl.getUpdateDate()),
                new LabelVersion(n4jl.getVersion())
        );
    }

    @Override
    public int countLabels() {
        return (int)repository.count();
    }
    
}
