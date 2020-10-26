Feature: Specific date foreign exchange rates check

Scenario: Verify that the Rates API for the specific past date fetch all the exchange rate
Given Rates API for Foreign Exchange for specific date
When I execute the rates for specific date API
Then I assert the success status of the response

Scenario: Verify that the Rates API for the specific date fetch all the exchange rate
Given Rates API for Foreign Exchange for specific date
When I execute the rates for specific date API
Then I assert the response

Scenario: Verify that the Rates API for the future date fetch all the exchange rate for today date
Given Rates API for Foreign Exchange for specific date
When I execute the rates API for a future date
Then I validate the response matches the current date exchange rates




