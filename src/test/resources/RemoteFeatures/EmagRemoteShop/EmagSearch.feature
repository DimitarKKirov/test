@Show
Feature: search

  Background:
    Given the user is on in "https://www.emag.bg/"

  Scenario: search for item
    When user enters search criteria "Harry"
    Then the user can see the results