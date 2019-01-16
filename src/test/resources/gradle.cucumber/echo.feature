Feature: Echo Server

  Scenario: when Client sends string Server echoes it back
    Given Server process is started
    When Client connects to server
    Then Server echoes client input