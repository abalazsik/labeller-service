
package com.mycompany.labeller.domain.repository;

import com.mycompany.labeller.domain.data.CreateLabelWithDate;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabelWithDate;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import java.util.Optional;
import java.util.stream.Stream;
import org.jmolecules.ddd.annotation.Repository;

/**
 *
 * @author ador
 */
@Repository
public interface LabelRepository {

    public Stream<Label> getAll(LabelRange range);
    public Stream<Label> getClassifiableLabels();
    public LabelId create(CreateLabelWithDate createLabel);
    public void delete(LabelId id);
    public void unlink(LabelId id);
    public Optional<Label> getById(LabelId id);
    public Optional<Label> getByName(LabelName name);
    public LabelVersion update(UpdateLabelWithDate update);
    public int countLabels();
    
}
