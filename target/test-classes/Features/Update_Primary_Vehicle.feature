Feature: Harley Enterprise : API Login Testing

  @ValidVinId
  Scenario: Successful login with valid mobile number for update primary vehicle with valid Vin Id
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle should be 200 and message "Vehicle Details Updated Successfully" and isPrimaryVehicle is "1"

  @InValidVinId
  Scenario: Successful login with valid mobile number for update primary vehicle with Invalid Vin Id
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with valid customer Identifier and Invalid Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for Invalid Vin Id should be 200 and message "Vehicle Could Not Be Set Primary" , errorCode 1024 and errorDescription "Unable to process Your Request"

  @BlankVinId
  Scenario: Successful login with valid mobile number for update primary vehicle with BlankVinId
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with valid customer Identifier and BlankVinId for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for blank vinId should be 200 and message "Invalid VIN No." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @InvalidCustomerIdentifier
  Scenario: Successful login with valid mobile number for update primary vehicle with InvalidCustomerIdentifier
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with Invalid customer Identifier and Valid VinId for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for invalid customer identifier should be 401 and message "User is not authorized" , errorCode 401

  @BlankCustomerIdentifier
  Scenario: Successful login with valid mobile number for update primary vehicle with BlankCustomerIdentifier
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with BlankCustomerIdentifier and valid VinId for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for BlankCustomerIdentifier should be 200 and message "Invalid Customer Identifier." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @SpecialCharCustomerIdentifier
  Scenario: Successful login with valid mobile number for update primary vehicle with SpecialCharCustomerIdentifier
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with SpecialCharCustomerIdentifier and valid VinId for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for SpecialCharCustomerIdentifier should be 200 and message "customerIdentifier contains special character(s)." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @InvalidToken
  Scenario: Successful login with valid mobile number for update primary vehicle with InvalidToken
    Given the login API is available and add headers and invalid access token for update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for invalid token should be 401 and message "User is not authorized" , errorCode 401

  @BlankToken
  Scenario: Successful login with valid mobile number for update primary vehicle with BlankToken
    Given the login API is available and add headers and Blank access token for update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for Blank token should be 200 and message "Invalid or null Token." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @InvalidUrl
  Scenario: Successful login with valid mobile number for update primary vehicle with InvalidUrl
    Given the login API is available and add headers and access token for update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle  for invalid url request with "POST" and url "https://dev-apim.heromotocorp.com/api/updatePrimaryVehicle"
    Then the response status for update primary vehicle for Invalid Vin Id should be 404 and message "Resource not found"

  @BlankRequestBody
  Scenario: Successful login with valid mobile number for update primary vehicle with BlankRequestBody
    Given the login API is available and add headers and access token for update primary vehicle
    When the user sends update primary vehicle request with "POST"
    Then the response status for update primary vehicle for empty request body should be 500

  @Invalid_appid
  Scenario: Successful login with valid mobile number and Invalid appid in update primary vehicle
    Given the login API is available and add headers for Invalid appid in update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for Invalid appid in update primary vehicle list should be 200 and message "Invalid AppId." , errorcode 1002 , errorDescription "Unable to process Your Request"

  @Invalid_Specificappid
  Scenario: Successful login with valid mobile number and Invalid specific appid in update primary vehicle
    Given the login API is available and add headers for Invalid specific appid in update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for Invalid specific appid in update primary vehicle list  should be 200 and message "Invalid Specific AppId." , errorcode 1002 , errorDescription "Unable to process Your Request"

  @Invalid_key
  Scenario: Successful login with valid mobile number and Invalid_ocp-apim-subscription-key in update primary vehicle
    Given the login API is available and add headers for Invalid_ocp-apim-subscription-key in update primary vehicle
    When user add requestBody with valid customer Identifier and Vin Id for update primary vehicle
    And the user sends update primary vehicle request with "POST"
    Then the response status for Invalid_ocp-apim-subscription-key in update primary vehicle should be 401 and message "Access denied due to invalid subscription key. Make sure to provide a valid key for an active subscription."
