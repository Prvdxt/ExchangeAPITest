Feature: Latest date foreign exchange rates check

Scenario: Verify that the Rates API for the latest date fetch only the success current exchange rate
Given Rates API for latest Foreign Exchange
When I execute the rates API
Then I assert the success status of the response

Scenario: Verify that the Rates API for the latest date fetch all the exchange rate
Given Rates API for latest Foreign Exchange
When I execute the rates API
Then I assert the response

Scenario: Verify that the Rates API for the latest date fetch bad status for incorrect URL
Given Rates API for latest Foreign Exchange
When I execute the rates API for incorrect URL
Then I assert the response as per the incorrect URL





