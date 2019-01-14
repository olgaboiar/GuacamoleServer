Feature: Echo Server

  Scenario: when Client sends string Server echoes it back
    Given Server process is started
    And Client connects to server
    When Client sends string
    Then Server responds with the same string