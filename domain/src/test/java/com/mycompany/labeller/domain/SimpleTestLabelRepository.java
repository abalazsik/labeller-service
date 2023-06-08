package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import com.mycompany.labeller.domain.repository.LabelRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author ador
 */
public class SimpleTestLabelRepository implements LabelRepository {

    private long idSeq = 1;
    private boolean unlinkWasCalled = false;
    protected List<Label> labels;

    public SimpleTestLabelRepository() {
        labels = new ArrayList<>();
    }
    
    public SimpleTestLabelRepository(Label label) {
        labels = new ArrayList<>();
        if (label != null) {
            labels.add(label);
        }
    }

    @Override
    public Stream<Label> getAll(LabelRange range) {
        return labels.stream().skip(range.getFrom().getValue()).limit(range.getLimit().getValue());
    }

    @Override
    public Stream<Label> getClassifiableLabels() {
        return this.labels.stream().filter(label -> NullableStringAttribute.getValue(label.getClassifierData()) != null);
    }

    @Override
    public LabelId create(CreateLabelWithDate createLabel) {
        Label label = new Label(new LabelId(idSeq++),
                createLabel.getName(),
                createLabel.getDescription(),
                createLabel.getClassifierData(),
                createLabel.getTechnical(),
                createLabel.getParent(),
                createLabel.getCreationTime(),
                new LabelUpdateDate(LabelCreationDate.getValue(createLabel.getCreationTime())),
                new LabelVersion(1L));

        this.labels.add(label);

        return label.getId();
    }

    @Override
    public void delete(LabelId id) {
        this.labels.removeIf(label -> label.getId().equals(id));
    }

    @Override
    public void unlink(LabelId id) {
        this.unlinkWasCalled = true;
    }

    @Override
    public Optional<Label> getById(LabelId id) {
        if (id == null || labels.isEmpty()) {
            return Optional.empty();
        }
        for (Label label : labels) {
            if (id.getValue() == label.getId().getValue()) {
                return Optional.of(label);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Label> getByName(LabelName name) {
        if (name == null || labels.isEmpty()) {
            return Optional.empty();
        }
        for (Label label : labels) {
            if (label.getName().equals(name)) {
                return Optional.of(label);
            }
        }
        return Optional.empty();
    }

    @Override
    public LabelVersion update(UpdateLabelWithDate update) {
        Label oldLabel = this.getById(update.getId()).get();

        Label newLabel = new Label(
                update.getId(),
                update.getName(),
                update.getDescription(),
                update.getClassifierData(),
                update.getTechnical(),
                update.getParent(),
                oldLabel.getCreationDate(),
                update.getUpdateDate(),
                update.getVersion().inc());

        this.delete(update.getId());
        this.labels.add(newLabel);
        return newLabel.getVersion();
    }
    
    public boolean unlinkWasCalled() {
        return this.unlinkWasCalled;
    }
    
    public void reset() {
        this.idSeq = 1;
        this.labels.clear();
        this.unlinkWasCalled = false;
    }

    @Override
    public int countLabels() {
        return this.labels.size();
    }

}
