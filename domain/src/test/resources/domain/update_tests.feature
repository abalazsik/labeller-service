Feature: Update label

    @admin @positive-case
    Scenario: Admin can update the label
        Given An initialized repository with label "name"
        And User is admin
        When Updating the label to name "name2"
        Then No exception was thrown

    @admin @positive-case
    Scenario: Update the label updates the name
        Given An initialized repository with label "name"
        And User is admin
        When Updating the label to name "name2"
        Then Getting the label
        And Label has name "name2"

    @rightless @negative-case
    Scenario: Rightless cannot update the label
        Given An initialized repository with label "name"
        And User is rightless
        When Updating the label to name "name2"
        Then Throws AccessRight exception

    @admin @negative-case
    Scenario: Admin can not update non-existing label
        Given An empty repository
        Given User is admin
        When Using non-existing id
        When Updating the label to name "name2"
        Then Throws Labeller exception with message "Label does not exists!"

    @admin @negative-case
    Scenario: Admin can not update a label to a taken name
        Given An initialized repository with label "name"
        And User is admin
        When Creating a label with name "name2"
        And Updating the label to name "name"
        Then Throws Labeller exception with message "Name taken"