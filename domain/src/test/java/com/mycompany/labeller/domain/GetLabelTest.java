package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelCreationDate;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelUpdateDate;
import com.mycompany.labeller.domain.exceptions.AccessRightException;
import com.mycompany.labeller.domain.services.LabelService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static com.mycompany.labeller.domain.Constants.*;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;

/**
 *
 * @author ador
 */
public class GetLabelTest {

    private LabelService labelService;

    private final LabelId id = new LabelId(1);
    private final LabelCreationDate creationDate = new LabelCreationDate(LocalDateTime.parse("2023-02-18T10:00:00"));
    private final LabelUpdateDate updateDate = new LabelUpdateDate(LocalDateTime.parse("2023-02-18T11:00:00"));

    @Test
    public void callsRepository() {
        //given
        Label expected = createTestLabel();

        labelService = new LabelService(
                new SimpleTestLabelRepository(expected),
                TEST_TIME_SOURCE);

        //when
        Optional<Label> result = labelService.getById(id, userWithGetByIdRight);

        //then
        assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    public void returnsAuditorData() {
        //given
        Label expected = createTestLabel();

        labelService = new LabelService(
                new SimpleTestLabelRepository(expected),
                TEST_TIME_SOURCE);

        //when
        Label result = labelService.getById(id, auditorUser).get();

        //then
        assertThat(result.getCreationDate()).isEqualTo(creationDate);
        assertThat(result.getUpdateDate()).isEqualTo(updateDate);
    }

    @Test
    public void removesAuditorData() {
        Label expected = createTestLabel();

        labelService = new LabelService(
                new SimpleTestLabelRepository(expected),
                TEST_TIME_SOURCE);

        //when
        Label result = labelService.getById(id, userWithGetByIdRight).get();

        //then
        assertThat(result.getCreationDate()).isNull();
        assertThat(result.getUpdateDate()).isNull();
    }

    @Test
    public void throwsExceptionWhenNotFound() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(createTestLabel()),
                TEST_TIME_SOURCE);

        //when
        Optional<Label> result = labelService.getById(new LabelId(2), userWithGetByIdRight);

        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void throwsExceptionWhenNoRights() {
        //given
        labelService = new LabelService(
                new SimpleTestLabelRepository(createTestLabel()),
                TEST_TIME_SOURCE);

        //when & then
        assertThatThrownBy(
                () -> labelService.getById(new LabelId(2), rightless)
        ).isInstanceOf(AccessRightException.class);
    }

    private Label createTestLabel() {
        return new Label(
                id,
                new LabelName("name"),
                new LabelDescription("description"),
                new LabelClassifierData(null),
                LabelTechnical.FALSE, null,
                creationDate,
                updateDate,
                new LabelVersion(1));
    }

}
