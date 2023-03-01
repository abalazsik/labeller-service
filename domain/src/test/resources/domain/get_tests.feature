Feature: Get label
    some description

    @auditor @positive-case
    Scenario: User can get the label by id
        Given An initialized repository
        Given User is auditor
        When Getting the label
        Then Label exists

    @admin @positive-case
    Scenario: User can get the label by id
        Given An initialized repository
        Given User is admin
        When Getting the label
        Then Label exists

    @auditor @positive-case
    Scenario: User cannot get a non-existing label
        Given An empty repository
        Given User is auditor
        When Getting the label
        Then Label does not exists

    @rightless @negative-case
    Scenario: Rightless User cannot get a label
        Given An initialized repository
        Given User is rightless
        When Getting the label
        Then Throws AccessRight exception