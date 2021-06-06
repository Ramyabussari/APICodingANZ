@RegisteraStation
Feature: OpenWeatherMAP API Tests

  @RegisteraStationwithoutAPIKey
  Scenario: Validate user is able to register a weather station without APIKEY
    Given user is able to access the API
    Then user registers a new station with below details
      | external_id | name | latitude | longitude | altitude | status code | status line               |
      | Test1       | Test | 1.5      | 2.3       | 222.3    | 401         | HTTP/1.1 401 Unauthorized |

  @RegistertwoStations
  Scenario Outline: Validate user is able to register two weather stations and status is created
    Given user is able to access the API
    Then user registers a new station with these details "<external_id>", "<name>","<latitude>","<longitude>","<altitude>","<status code>"

    Examples:
      | external_id  | name                       | latitude | longitude | altitude | status code |
      | DEMO_TEST001 | Team Demo Test Station 001 | 33.33    | -122.43   | 222      | 201         |
      | DEMO_TEST002 | Team Demo Test Station 002 | 44.44    | -122.44   | 111      | 201         |

  @getRgisteredStation
  Scenario Outline: Validate user registered a weather stations are stored in DB
    Given user is able to access the API
    Then user requests the station details and validate the registered stations are available with "<externalID>"

    Examples:
      | externalID   |
      | DEMO_TEST001 |
      | DEMO_TEST002 |