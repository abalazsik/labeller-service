Feature: Create label

    @admin @positive-case
    Scenario: Admin can create a technical label
        Given An empty repository
        Given User is admin
        When Creating a technical label
        Then Returns an id
        Then No exception was thrown

    @admin @positive-case
    Scenario: Admin can create a non-technical label
        Given An empty repository
        Given User is admin
        When Creating a non-technical label
        Then Returns an id
        Then No exception was thrown

    @positive-case
    Scenario: User can create a non-technical label
        Given An empty repository
        Given User with rights "Label:Create"
        When Creating a non-technical label
        Then Returns an id
        And No exception was thrown

    @admin @negative-case
    Scenario: Admin can not create a label with the same name
        Given An initialized repository with label "name"
        And User is admin
        When Creating a label with name "name"
        Then Throws Labeller exception with message "already exists"

    @rightless @negative-case
    Scenario: Rightless user can not create label
        Given An empty repository
        Given User is rightless
        When Creating a non-technical label
        Then Throws AccessRight exception
