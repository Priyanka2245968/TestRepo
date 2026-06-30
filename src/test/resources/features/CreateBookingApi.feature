@CreateBookingApi
Feature: Create Booking API Tests

  Scenario: BOK-25-TC-01 - Positive — Create a new booking with valid payload
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I execute step 1: "Send POST /booking with header Content-Type: application/json and body {'firstname':'Swarup','lastname':'Roy','totalprice':12000,'depositpaid':true,'bookingdates':{'checkin':'2026-07-10','checkout':'2026-07-12'},'additionalneeds':'Breakfast'}"
    Then the test should complete successfully

  Scenario: BOK-25-TC-02 - Negative — Invalid Input: Missing required field
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I execute step 1: "Send POST /booking with header Content-Type: application/json and body {'lastname':'Roy','totalprice':12000,'depositpaid':true,'bookingdates':{'checkin':'2026-07-10','checkout':'2026-07-12'},'additionalneeds':'Breakfast'}"
    Then the test should complete successfully

  Scenario: BOK-25-TC-03 - Boundary — Checkin date equal to or after checkout date
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I execute step 1: "Send POST /booking with header Content-Type: application/json and body {'firstname':'Swarup','lastname':'Roy','totalprice':12000,'depositpaid':true,'bookingdates':{'checkin':'2026-07-12','checkout':'2026-07-12'},'additionalneeds':'Breakfast'}"
    Then the test should complete successfully