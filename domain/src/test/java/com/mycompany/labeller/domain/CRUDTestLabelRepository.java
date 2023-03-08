package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author ador
 */
public class CRUDTestLabelRepository extends SimpleTestLabelRepository {

    private long idSeq = 1;
    private boolean unlinkWasCalled = false;

    public CRUDTestLabelRepository() {
        super(null);
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

    @Override
    public void delete(LabelId id) {
        this.labels.removeIf(label -> label.getId().equals(id));
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
    public Stream<Label> getClassifiableLabels() {
        return this.labels.stream().filter(label -> NullableStringAttribute.getValue(label.getClassifierData()) != null);
    }

    @Override
    public void unlink(LabelId id) {
        this.unlinkWasCalled = true;
    }

    public boolean unlinkWasCalled() {
        return this.unlinkWasCalled;
    }

    public void reset() {
        this.idSeq = 1;
        this.labels = new ArrayList<>();
        this.unlinkWasCalled = false;
    }

}
