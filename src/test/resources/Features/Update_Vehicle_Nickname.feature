Feature: Harley Enterprise : API Login Testing

  Background: 
    Given the login API is available and add headers and access token for update vehicle nickname

  @ValidVinId
  Scenario: Successful login with valid mobile number for update vehicle nickname with valid Vin Id
    When user add requestBody with valid customer Identifier and Vin Id for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname should be 200 and message "Vehicle Details Updated Successfully" , vehicle nickname "Aatish" and updated message "Vehicle Nick Name Updated Successfully"

  @InValidVinId
  Scenario: Successful login with valid mobile number for update vehicle nickname with Invalid Vin Id
    When user add requestBody with valid customer Identifier and Invalid Vin Id for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname for Invalid Vin Id should be 200 and message "Vehicle Details Could Not Be Updated" , errorCode 1023 and errorDescription "Unable to process Your Request"

  @InvalidNickname
  Scenario: Successful login with valid mobile number for update vehicle nickname with invalid nick name
    When user add requestBody with valid customer Identifier ,Vin Id and invalid nick name for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname for Invalid nick name should be 200 and message "Vehicle Name Could Not Be Updated." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @BlankVinId
  Scenario: Successful login with valid mobile number for update vehicle nickname with BlankVinId
    When user add requestBody with valid customer Identifier, valid nick name and BlankVinId for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname for blank vinId should be 200 and message "Invalid VIN No." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @specialcharVinId
  Scenario: Successful login with valid mobile number for update vehicle nickname with specialcharVinId
    When user add requestBody with valid customer Identifier, valid nick name and specialcharVinId for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname for specialcharVinId should be 200 and message "vinId contains special character(s)." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @SpecialcharNickname
  Scenario: Successful login with valid mobile number for update vehicle nickname with SpecialcharNickname
    When user add requestBody with valid customer Identifier ,Vin Id and SpecialcharNickname for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nickname for SpecialcharNickname should be 200 and message "Vehicle Name Could Not Be Updated." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @SpaceinbtwNickname
  Scenario: Successful login with valid mobile number for update vehicle nickname with Space in btw Nickname
    When user add requestBody with valid customer Identifier ,Vin Id and Space in btw Nickname for update vehicle nickname "   Aatish"
    And the user sends update vehicle nickname request with "POST"
    Then the response status for Space in btw Nickname in update vehicle nickname should be 200 and message "Vehicle Details Updated Successfully" , vehicle nickname "Aatish" and updated message "Vehicle Nick Name Updated Successfully"

  @InvalidCustomerIdentifier
  Scenario: Successful login with valid mobile number for update primary vehicle with InvalidCustomerIdentifier
    When user add requestBody with Invalid customer Identifier ,Vin Id and valid nick name for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for invalid customer identifier should be 401 and message "User is not authorized" , errorCode 401

  @SpecialCharCustomerIdentifier
  Scenario: Successful login with valid mobile number for update vehicle nickname with SpecialCharCustomerIdentifier
    When user add requestBody with SpecialCharCustomerIdentifier and valid VinId for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for SpecialCharCustomerIdentifier should be 200 and message "customerIdentifier contains special character(s)." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @BlankIdentifier
  Scenario: Successful login with valid mobile number for update vehicle nickname with BlankIdentifier
    When user add requestBody with BlankIdentifier and valid VinId for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for BlankIdentifier should be 200 and message "Invalid Customer Identifier." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @InvalidToken
  Scenario: Successful login with valid mobile number for update vehicle nickname with InvalidToken
    Given the login API is available and add headers and invalid access token for update vehicle nickname
    When user add requestBody with valid customer Identifier and Vin Id for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for invalid token should be 401 and message "User is not authorized" , errorCode 401

  @BlankToken
  Scenario: Successful login with valid mobile number for update vehicle nickname with BlankToken
    Given the login API is available and add headers and BlankToken for update vehicle nickname
    When user add requestBody with valid customer Identifier and Vin Id for update vehicle nickname
    And the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for Blank token should be 200 and message "Invalid or null Token." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @InvalidUrl
  Scenario: Successful login with valid mobile number for update vehicle nickname with InvalidUrl
    When user add requestBody with valid customer Identifier and Vin Id for update vehicle nickname
    And the user sends update vehicle nickname request with "POST" and url "https://dev-apim.heromotocorp.com/api/updateVehicleDetails"
    Then the response status for update vehicle nickname for Invalid url should be 404 and message "Resource not found"

  @BlankRequestBody
  Scenario: Successful login with valid mobile number for update vehicle nick name with BlankRequestBody
    When the user sends update vehicle nickname request with "POST"
    Then the response status for update vehicle nick name for empty request body should be 500
