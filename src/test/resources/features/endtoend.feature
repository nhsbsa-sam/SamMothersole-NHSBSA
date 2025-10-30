Feature: NHS Cost Checker - UK Citizen

  # Full end to end journey with help as the end result.
  Scenario: UK citizen enters circumstances and receives the help result
    Given I am on the NHS cost checker start page
    When I select I live in the UK
    And I answer the questions about my circumstances
    Then I should see whether I get help with NHS costs

# Verifies if error message appears when continuing without any input in DOB input field.
  Scenario: User submits date of birth without entering any values and should receive error message
    Given I open the checker to the date of birth page
    When I continue without entering a date of birth
    Then I should see a date of birth validation error

#Veryfies service does not allow help if user selects Northern IReland
  Scenario: User living in Northern Ireland is shown no help and the journey stops
    Given I am on the NHS cost checker start page for NI
    When I select I live in Northern Ireland
    Then I should be told this service does not proceed for Northern Ireland
    And I should not be taken to the GP practice question

# Verifies if footer links work correctly
  Scenario: Verify footer links are working
    Given I am on the NHS cost checker start page for footer testing
    When I click each footer link and verify it opens correctly
    Then all footer links should be valid







