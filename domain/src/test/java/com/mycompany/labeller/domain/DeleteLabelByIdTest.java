package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.exceptions.AccessRightException;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import com.mycompany.labeller.domain.services.LabelService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static com.mycompany.labeller.domain.Constants.*;
import com.mycompany.labeller.domain.data.CreateLabel;

/**
 *
 * @author ador
 */
public class DeleteLabelByIdTest {

    private LabelService labelService;

    @Test
    public void callsRepositoryRemovesLabel() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(),
                TEST_TIME_SOURCE);
        LabelId id = labelService.create(createTestLabel(false), admin);

        //when
        labelService.delete(id, userWithDeleteRight);

        //then
        assertThat(labelService.getById(id, userWithGetByIdRight).isEmpty()).isTrue();
    }

    @Test
    public void throwsErrorWhenNoRightToDelete() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(),
                TEST_TIME_SOURCE);
        LabelId id = labelService.create(createTestLabel(false), admin);

        //when & then
        assertThatThrownBy(
                () -> labelService.delete(id, rightless)
        ).isInstanceOf(AccessRightException.class);
    }

    @Test
    public void throwsErrorWhenDeletingNonExistentLabel() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(),
                TEST_TIME_SOURCE);
        LabelId id = labelService.create(createTestLabel(false), admin);

        //when & then
        assertThatThrownBy(
                () -> labelService.delete(new LabelId(2),
                        userWithDeleteRight)
        ).isInstanceOf(LabellerException.class);
    }

    @Test
    public void throwsErrorWhenDeletingTechnicalLabel() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(),
                TEST_TIME_SOURCE);
        LabelId id = labelService.create(createTestLabel(true), admin);

        //when & then
        assertThatThrownBy(
                () -> labelService.delete(id,
                        userWithDeleteRight)
        ).isInstanceOf(LabellerException.class);
    }

    private CreateLabel createTestLabel(boolean technical) {
        return new CreateLabel(new LabelName("name"),
                new LabelDescription("description"),
                new LabelClassifierData(null),
                LabelTechnical.of(technical), null);
    }
}
