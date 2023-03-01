package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import java.util.ArrayList;

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
    public void update(UpdateLabelWithDate update) {
        Label oldLabel = this.getById(update.getId()).get();
        
        Label newLabel = new Label(
                update.getId(), 
                update.getName(), 
                update.getDescription(), 
                update.getClassifierData(), 
                update.getTechnical(), 
                update.getParent(), oldLabel.getCreationDate(), update.getUpdateDate());
        
        this.delete(update.getId());
        this.labels.add(newLabel);
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
                new LabelUpdateDate(createLabel.getCreationTime().getValue()));

        this.labels.add(label);
        
        return label.getId();
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
