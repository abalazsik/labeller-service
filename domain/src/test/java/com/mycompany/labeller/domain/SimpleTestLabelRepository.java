package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
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

    protected List<Label> labels;

    public SimpleTestLabelRepository(Label label) {
        labels = new ArrayList<>();
        if (label != null) {
            labels.add(label);
        }
    }

    @Override
    public Stream<Label> getAll(LabelRange range) {
        return labels.stream().skip(range.getFrom()).limit(range.getLimit());
    }

    @Override
    public Stream<Label> getClassifiableLabels() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LabelId create(CreateLabelWithDate createLabel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(LabelId id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void unlink(LabelId id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
