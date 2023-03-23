package com.mycompany.labeller.neo4j.service;

import com.mycompany.labeller.commons.CachedLabelId;
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
import com.mycompany.labeller.neo4j.Neo4JLabel;
import com.mycompany.labeller.neo4j.repository.Neo4JLabelRepository;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class Neo4JLabelStorage implements LabelRepository {
    
    @Autowired
    private Neo4JLabelRepository repository;
    
    @Override
    public Stream<Label> getAll(LabelRange range) {
        int from = range.getFrom().getValue();
        int limit = range.getLimit().getValue();
        return repository.findAll(PageRequest.of(from / limit, limit)).stream().map(this::toDomain);
    }
    
    @Override
    public Stream<Label> getClassifiableLabels() {
        return repository.getByClassifiable().map(this::toDomain);
    }
    
    @Override
    public LabelId create(CreateLabelWithDate createLabel) {
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
        repository.deleteById(id.getValue());
    }
    
    @Override
    public void unlink(LabelId id) {
        repository.unlink(id.getValue());
    }
    
    @Override
    public Optional<Label> getById(LabelId id) {
        return repository.findById(id.getValue()).map(this::toDomain);
    }
    
    @Override
    public Optional<Label> getByName(LabelName name) {
        return repository.getByName(name.getValue()).map(this::toDomain);
    }
    
    @Override
    public LabelVersion update(UpdateLabelWithDate update) {
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
        return new Label(
                CachedLabelId.of(n4jl.getId()),
                new LabelName(n4jl.getName()),
                new LabelDescription(n4jl.getDescription()),
                new LabelClassifierData(n4jl.getDescription()),
                LabelTechnical.of(n4jl.isTechnical()),
                CachedLabelId.of(n4jl.getChildOf().getId()),
                new LabelCreationDate(n4jl.getCreationDate()),
                new LabelUpdateDate(n4jl.getUpdateDate()),
                new LabelVersion(n4jl.getVersion())
        );
    }
    
}
