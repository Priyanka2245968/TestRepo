@CreateBookingApi
Feature: Create Booking API Tests

  Scenario: BOK-25-TC-01 - Positive — Create a new booking with valid payload
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I send a POST request to "/booking" with the following payload:
      | firstname | lastname | totalprice | depositpaid | bookingdates.checkin | bookingdates.checkout | additionalneeds |
      | Swarup    | Roy      | 12000      | true        | 2026-07-10           | 2026-07-12           | Breakfast       |
    Then the response should have a "bookingid" field
    And the response "booking.firstname" field should be "Swarup"
    And the response "booking.lastname" field should be "Roy"
    And the response "booking.totalprice" field should be 12000
    And the response "booking.depositpaid" field should be true
    And the response "booking.bookingdates.checkin" field should be "2026-07-10"
    And the response "booking.bookingdates.checkout" field should be "2026-07-12"
    And the response "booking.additionalneeds" field should be "Breakfast"

  Scenario: BOK-25-TC-02 - Negative — Invalid Input: Missing required field
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I send a POST request to "/booking" with the following payload:
      | lastname | totalprice | depositpaid | bookingdates.checkin | bookingdates.checkout | additionalneeds |
      | Roy      | 12000      | true        | 2026-07-10           | 2026-07-12           | Breakfast       |
    Then the response should have a "reason" field
    And the response "reason" field should contain "Missing firstname"

  Scenario: BOK-25-TC-03 - Boundary — Checkin date equal to or after checkout date
    Given I navigate to "https://restful-booker.herokuapp.com"
    When I send a POST request to "/booking" with the following payload:
      | firstname | lastname | totalprice | depositpaid | bookingdates.checkin | bookingdates.checkout | additionalneeds |
      | Swarup    | Roy      | 12000      | true        | 2026-07-12           | 2026-07-12           | Breakfast       |
    Then the response should have a "reason" field
    And the response "reason" field should contain "Checkin date should be before checkout date"
