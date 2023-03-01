package com.mycompany.labeller.domain.services;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabel;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.exceptions.AccessRightException;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import com.mycompany.labeller.domain.repository.LabelRepository;
import com.mycompany.labeller.domain.user.IUser;
import com.mycompany.labeller.domain.user.Rights;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author ador
 */
public class LabelService {

    private final LabelRepository repository;
    private final TimeSource timeSource;

    public LabelService(LabelRepository repository, TimeSource timeSource) {
        this.repository = repository;
        this.timeSource = timeSource;
    }

    public Stream<Label> getAll(LabelRange range, IUser user) {
        checkRight(Rights.LabelGetAll, user);
        return repository.getAll(range).map(label -> audit(label, user));
    }

    @Deprecated
    public Stream<Label> getClassified(IUser user) {
        checkRight(Rights.LabelGetClassified, user);
        return repository.getClassifiableLabels().map(label -> audit(label, user));
    }

    public LabelId create(CreateLabel createLabel, IUser user) {
        checkRight(Rights.LabelCreate, user);

        if (repository.getByName(createLabel.getName()).isPresent()) {
            throw new LabellerException("already exists");
        }

        checkParent(createLabel.getParent());
        
        return repository.create(new CreateLabelWithDate(
                createLabel.getName(),
                createLabel.getDescription(),
                createLabel.getClassifierData(),
                createLabel.getTechnical(),
                createLabel.getParent(),
                new LabelCreationDate(timeSource.now())));
    }

    public void delete(LabelId id, IUser user) {
        checkRight(Rights.LabelDelete, user);
        Optional<Label> optional = repository.getById(id);

        if (optional.isEmpty()) {
            throw new LabellerException("Label does not exists!");
        }

        Label label = optional.get();

        if (label.getTechnical().value()) {
            throw new LabellerException("Cannot delete a technical label!");
        }

        repository.unlink(id);
        repository.delete(id);
    }

    public Optional<Label> getById(LabelId id, IUser user) {
        checkRight(Rights.LabelGetById, user);
        return repository.getById(id).map(label -> audit(label, user));
    }

    public void update(UpdateLabel update, IUser user) {
        checkRight(Rights.LabelUpdate, user);

        if (repository.getById(update.getId()).isEmpty()) {
            throw new LabellerException("Label does not exists!");
        }

        Optional<Label> byName = repository.getByName(update.getName());
        if (byName.isPresent() && !byName.get().getId().equals(update.getId())) {
            throw new LabellerException("Name taken");
        }
        
        checkParent(update.getParent());

        UpdateLabelWithDate updateLabelWithDate = new UpdateLabelWithDate(
                update.getId(),
                update.getName(),
                update.getDescription(),
                update.getClassifierData(),
                update.getTechnical(),
                update.getParent(),
                new LabelUpdateDate(timeSource.now()));

        repository.update(updateLabelWithDate);
    }

    private void checkRight(String right, IUser user) {
        if (!user.hasRight(right)) {
            throw new AccessRightException("User has no right " + right);
        }
    }

    private Label audit(Label label, IUser user) {
        LabelUpdateDate updateDate = null;
        LabelCreationDate creationDate = null;

        if (user.hasRight(Rights.LabelAUDIT)) {
            updateDate = label.getUpdateDate();
            creationDate = label.getCreationDate();
        }

        return new Label(label.getId(),
                label.getName(),
                label.getDescription(),
                label.getClassifierData(),
                label.getTechnical(),
                label.getParent(),
                creationDate,
                updateDate);
    }

    private void checkParent(LabelId parentId) {
        if (parentId != null) {

            Optional<Label> parent = repository.getById(parentId);

            if (parent.isEmpty()) {
                throw new LabellerException("Parent does not exist!");
            }
        }
    }

}
