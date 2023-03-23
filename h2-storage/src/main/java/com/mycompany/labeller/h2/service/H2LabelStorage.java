package com.mycompany.labeller.h2.service;

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
import com.mycompany.labeller.h2.H2Label;
import com.mycompany.labeller.h2.repository.H2LabelRepostitory;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class H2LabelStorage implements LabelRepository {

    @Autowired
    private H2LabelRepostitory repository;

    @Override
    public Stream<Label> getAll(LabelRange range) {
        return repository.getAll(range.getFrom().getValue(), range.getLimit().getValue())
                .map(this::toDomain);
    }

    @Override
    public Stream<Label> getClassifiableLabels() {
        return repository.getByClassifiable().map(this::toDomain);
    }

    @Override
    public LabelId create(CreateLabelWithDate createLabel) {
        H2Label h2Label = new H2Label(
                null,
                createLabel.getName().getValue(),
                NullableStringAttribute.getValue(createLabel.getDescription()),
                NullableStringAttribute.getValue(createLabel.getClassifierData()),
                createLabel.getTechnical().value(),
                null,
                LabelCreationDate.getValue(createLabel.getCreationTime()),
                LabelUpdateDate.getValue(createLabel.getCreationTime()),
                createLabel.getVersion().getValue()
        );

        h2Label = repository.save(h2Label);

        return h2Label.getId();
    }

    @Override
    public void delete(LabelId id) {
        repository.delete(id.getValue());
    }

    @Override
    public Optional<Label> getById(LabelId id) {
        return repository.getById(id.getValue()).map(this::toDomain);
    }

    @Override
    public void unlink(LabelId id) {
        repository.unlink(id.getValue());
    }

    @Override
    public LabelVersion update(UpdateLabelWithDate update) {

        H2Label h2Label = new H2Label(
                update.getId(),
                update.getName().getValue(),
                NullableStringAttribute.getValue(update.getDescription()),
                NullableStringAttribute.getValue(update.getClassifierData()),
                update.getTechnical().value(),
                update.getParent(),
                repository.getCreateDate(update.getId().getValue()),
                LabelUpdateDate.getValue(update.getUpdateDate()),
                update.getVersion().getValue());

        repository.save(h2Label);

        return update.getVersion();
    }

    @Override
    public Optional<Label> getByName(LabelName name) {
        return repository.getByName(name.getValue()).map(this::toDomain);
    }

    private Label toDomain(H2Label h2Label) {
        return new Label(h2Label.getId(),
                new LabelName(h2Label.getName()),
                new LabelDescription(h2Label.getDescription()),
                new LabelClassifierData(h2Label.getClassifierData()),
                LabelTechnical.of(h2Label.isTechnical()),
                h2Label.getParent(),
                new LabelCreationDate(h2Label.getCreationDate()),
                new LabelUpdateDate(h2Label.getUpdateDate()),
                new LabelVersion(h2Label.getVersion())
        );
    }

}
