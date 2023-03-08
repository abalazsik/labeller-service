Feature: Get label for feature

    @admin @positive-case
    Scenario: Can find labels
        Given A preloaded repository with: 
            |name   |classifier_data|
            |spring |spring         |
            |test   |test           |
        And User is admin
        When Getting labels for the text "spring test"
        Then Selects label "spring"
        And Selects label "test"


    @admin @positive-case
    Scenario: Does not select not related label
        Given A preloaded repository with: 
            |name   |classifier_data |
            |spring |spring          |
            |other  |"something else"|
        And User is admin
        When Getting labels for the text "some spring related text"
        Then Selects label "spring"
        And Does not select label "other"
