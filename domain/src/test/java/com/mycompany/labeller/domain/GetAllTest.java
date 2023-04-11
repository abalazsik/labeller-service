
package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelRangeFrom;
import com.mycompany.labeller.domain.data.attributes.LabelRangeLimit;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.services.LabelService;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
/**
 *
 * @author ador
 */
public class GetAllTest {

    @Test
    public void expect_getAll5_returns5() {
        SimpleTestLabelRepository repository = new SimpleTestLabelRepository();
        
        LabelService service = new LabelService(repository, Constants.TEST_TIME_SOURCE);
        
        for (int i = 0; i < 10; i++) {
            service.create(createLabel(i), Constants.admin);
        }
        
        Stream<Label> result = service.getAll(
                new LabelRange(
                        new LabelRangeFrom(2), 
                        new LabelRangeLimit(5)), 
                Constants.auditorUser);
        
        assertThat(result).hasSize(5);
    }
    
    @Test
    public void expect_getAll_auditsLabels() {
        SimpleTestLabelRepository repository = new SimpleTestLabelRepository();
        
        LabelService service = new LabelService(repository, Constants.TEST_TIME_SOURCE);
        
        for (int i = 0; i < 10; i++) {
            service.create(createLabel(i), Constants.admin);
        }
        
        final LabelRange range = new LabelRange(
                        new LabelRangeFrom(2), 
                        new LabelRangeLimit(5));
        
        Stream<Label> result = service.getAll(
                range, 
                Constants.userWithGetAllRight);
        
        assertThat(result).first().matches(label -> label.getCreationDate() == null && label.getUpdateDate() == null);
        
        result = service.getAll(
                range, 
                Constants.auditorUser);

        assertThat(result).first().matches(label -> label.getCreationDate() != null && label.getUpdateDate() != null);
        
    }
    
    public CreateLabel createLabel(int i) {
        return new CreateLabel(new LabelName("name" + i), null, null, LabelTechnical.TRUE, null);
    }
    
    
}
