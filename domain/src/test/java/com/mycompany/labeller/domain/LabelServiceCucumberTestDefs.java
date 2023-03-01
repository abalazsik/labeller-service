package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.data.CreateLabel;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelId;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.repository.LabelRepository;
import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.domain.user.IUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.mycompany.labeller.domain.Constants.*;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.UpdateLabel;
import com.mycompany.labeller.domain.exceptions.AccessRightException;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author ador
 */
public class LabelServiceCucumberTestDefs {

    private IUser user;
    private LabelRepository repository;
    private LabelId actualId;
    private Exception exception = null;
    private Optional<Label> requestedLabel;

    @Given("User is admin")
    public void user_is_admin() {
        user = Constants.admin;
    }

    @Given("User is auditor")
    public void user_is_auditor() {
        user = Constants.auditorUser;
    }

    @Given("User with rights {string}")
    public void user_with_rights(String rights) {
        user = new TestUser(rights.split(";"));
    }

    @Given("User is rightless")
    public void user_is_rightless() {
        user = Constants.rightless;
    }

    @Given("An empty repository")
    public void empty_repository() {
        repository = new CRUDTestLabelRepository();
    }

    @Given("An initialized repository")
    public void initialized_repository() {
        repository = new CRUDTestLabelRepository();
        actualId = new LabelService(repository, TEST_TIME_SOURCE).create(createTestLabel(false), admin);
    }

    @Given("An initialized repository with label {string}")
    public void initialized_repository_with_name(String name) {
        repository = new CRUDTestLabelRepository();
        actualId = new LabelService(repository, TEST_TIME_SOURCE).create(createTestLabel(name, false), admin);
    }

    @When("Creating a technical label")
    public void creating_technical_label() {
        try {
            actualId = new LabelService(repository, TEST_TIME_SOURCE).create(createTestLabel(true), user);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("Creating a label with name {string}")
    public void creating_label_with_name(String name) {
        try {
            actualId = new LabelService(repository, TEST_TIME_SOURCE).create(createTestLabel(name, true), user);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("Creating a non-technical label")
    public void creating_non_technical_label() {
        try {
            actualId = new LabelService(repository, TEST_TIME_SOURCE).create(createTestLabel(false), user);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("Updating the label to name {string}")
    public void update_label_to_name(String name) {
        try {
            new LabelService(repository, TEST_TIME_SOURCE).update(updateTestLabel(name), user);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("Getting the label")
    public void getting_the_label() {
        try {
            requestedLabel = new LabelService(repository, TEST_TIME_SOURCE).getById(actualId, user);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("Returns an id")
    public void returns_an_id() {
        assertThat(actualId).isNotNull();
    }

    @Then("Label exists")
    public void label_exists() {
        assertThat(requestedLabel).isNotEmpty();
    }

    @Then("Label does not exists")
    public void label_not_exists() {
        assertThat(requestedLabel).isEmpty();
    }

    @Then("Throws AccessRight exception")
    public void throws_access_right_ex() {
        assertThat(exception).isExactlyInstanceOf(AccessRightException.class);
        exception = null;
    }

    @Then("Throws Labeller exception")
    public void throws_labeller_ex() {
        assertThat(exception).isInstanceOf(LabellerException.class);
        exception = null;
    }

    @Then("Throws Labeller exception with message {string}")
    public void throws_labeller_ex(String message) {
        assertThat(exception).isInstanceOf(LabellerException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        exception = null;
    }

    @Then("No exception was thrown")
    public void no_exception_thrown() {
        assertThat(exception).isNull();
    }
    
    @Then("Label has name {string}")
    public void label_has_name(String name) {
        assertThat(requestedLabel.get().getName().getValue()).isEqualTo(name);
    }

    private CreateLabel createTestLabel(boolean technical) {
        return createTestLabel("name", technical);
    }

    private CreateLabel createTestLabel(String name, boolean technical) {
        return new CreateLabel(new LabelName(name),
                new LabelDescription("description"),
                new LabelClassifierData(null),
                LabelTechnical.of(technical), null);
    }

    private UpdateLabel updateTestLabel(String name) {
        return new UpdateLabel(actualId, new LabelName(name),
                new LabelDescription("description"), null,
                LabelTechnical.TRUE, null);
    }

}
