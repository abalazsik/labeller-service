package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.UpdateLabel;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.exceptions.EntityDetachedException;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import com.mycompany.labeller.domain.services.LabelService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ador
 */
public class MiscCRUDTests {

    private SimpleTestLabelRepository repository;
    private LabelService service;

    @BeforeEach
    public void beforeEach() {
        repository = new SimpleTestLabelRepository();
        service = new LabelService(repository, Constants.TEST_TIME_SOURCE);
    }

    @Test
    public void expect_updateThrowsExceptionWhenUpdatingWithWrongVersion() {
        LabelId id = service.create(
                new CreateLabel(
                        new LabelName("name"), 
                        null, null, 
                        LabelTechnical.TRUE, null
                ), Constants.admin);

        Assertions.assertThatThrownBy(
                () -> service.update(
                        new UpdateLabel(id,
                                new LabelName("name"),
                                null, null,
                                LabelTechnical.TRUE, null,
                                new LabelVersion(99)),
                        Constants.admin)
        ).isInstanceOf(EntityDetachedException.class);

    }

    @Test
    public void expect_createThrowsExceptionWhenCreatingLabelWithInvalidParent() {
        Assertions.assertThatThrownBy(
                () -> service.create(
                        new CreateLabel(
                                new LabelName("name"),
                                null, null,
                                LabelTechnical.TRUE, new LabelId(88)),
                        Constants.admin)
        ).isInstanceOf(LabellerException.class);
    }

    @Test
    public void expect_updateThrowsExceptionWhenCreatingLabelWithInvalidParent() {
        LabelId id = service.create(
                new CreateLabel(
                        new LabelName("name"), 
                        null, null, 
                        LabelTechnical.TRUE, null
                ), Constants.admin);

        Assertions.assertThatThrownBy(
                () -> service.update(
                        new UpdateLabel(id,
                                new LabelName("name"),
                                null, null,
                                LabelTechnical.TRUE, new LabelId(88),
                                new LabelVersion(1)),
                        Constants.admin)
        ).isInstanceOf(LabellerException.class);
    }

    @Test
    public void expect_createWithExistingParentSucceeds() {
        LabelId parentId = service.create(
                new CreateLabel(
                        new LabelName("parent"),
                        null, null,
                        LabelTechnical.TRUE, null
                ), Constants.admin);

        service.create(
                new CreateLabel(
                        new LabelName("child"),
                        null, null,
                        LabelTechnical.TRUE, parentId
                ), Constants.admin);

    }

}
