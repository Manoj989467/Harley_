Feature: Harley Enterprise : API Login Testing

  @validCustomerIdentifier
  Scenario: Successful login with valid CustomerIdentifier
    Given the login API is available and add headers for get vehicle detail list
    When user add requestBody for get vin detail list "9905662637" ,"mobile" and "sms"
    And the user sends a get vin detail list Otp request with "POST"
    Then the response status for get vin detail list Otp should be 200 and message "OTP Generated Successfully" and loginId "9905662637"
    When user add request for get vehicle detail list along with loginId "9905662637" , isdCode "+91" ,deviceType "IOS" ,osVersion "15.1" ,appVersion "10" and deviceModel "x86_64"
    And the user sends a get vehicle detail list request with "POST"
    Then Validate the otp Generated for get vehicle detail list having the response code as 200 and verify message "OTP Verified Successfully"
    Given the login API is available and add headers and access token for get vehicle detail list
    When user add requestBody with valid customer Identifier for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for get vin detail list Otp should be 200 and message "Vehicle Details"

  @InvalidCustomerIdentifier
  Scenario: Successful login with valid mobile number for get vehicle detail list with Invalid customer Identifier
    Given the login API is available and add headers for invalid customer identifier and access token for get vehicle detail list
    When user add requestBody with Invalid customer Identifier for get vehicle detail list
    And the user sends a Invalid customer identifier for get vin detail list otp request with "POST"
    Then the response status for Invalid customer identifier for get vin detail list Otp should be 401 and message "User is not authorized" and errorCode 401

  @BlankIdentifier
  Scenario: Successful login with valid mobile number for get vehicle detail list with Blank customer Identifier
    Given the login API is available and add headers for Blank customer identifier and access token for get vehicle detail list
    When user add requestBody with Blank customer Identifier for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for Blank customer identifier for get vin detail list Otp should be 401 and message "User is not authorized" and errorCode 401

  @Invalid_appid
  Scenario: Successful login with valid mobile number and Invalid appid in get vehicle list
    Given the login API is available and add headers for Invalid appid in get vehicle list
    When user add requestBody with valid customer Identifier for Invalid appid in get vehicle list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for Invalid appid in get vehicle list Otp should be 200 and message "Invalid AppId." , errorcode 1002 , errorDescription "Unable to process Your Request"

  @Invalid_Specificappid
  Scenario: Successful login with valid mobile number and Invalid specific appid in get vehicle list
    Given the login API is available and add headers for Invalid specific appid in get vehicle list
    When user add requestBody with valid customer Identifier for Invalid specific appid in get vehicle list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for Invalid specific appid in get vehicle list Otp should be 200 and message "Invalid Specific AppId." , errorcode 1002 , errorDescription "Unable to process Your Request"
  
  @Special_char_customer_Identifier
  Scenario: Successful login with valid mobile number and Special_char_customer_Identifier in get vehicle list
    Given the login API is available and add headers for Special_char_customer_Identifier in get vehicle list
    When user add requestBody for Special_char_customer_Identifier in get vehicle list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for Special_char_customer_Identifier in get vehicle list Otp should be 200 and message "Invalid Customer Identifier." , errorcode 1002 , errorDescription "Unable to process Your Request"

  @Old_Token
  Scenario: Successful login with valid mobile number for get vehicle detail list with Old access Token
    Given the login API is available and add headers for Old access Token for get vehicle detail list
    When user add requestBody with Old access Token for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for Old access Token for get vin detail list Otp should be 401 and message "User is not authorized" and errorCode 401

  @Blank_Token
  Scenario: Successful login with valid mobile number for get vehicle detail list with Old access Token
    Given the login API is available and add headers for blank access Token for get vehicle detail list
    When user add requestBody with blank access Token for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for blank access Token for get vin detail list Otp should be 200 and message "Invalid or null Token." , errorCode 1002 and errorDescription "Unable to process Your Request"

  @Empty_Body
  Scenario: Successful login with Empty Request Body for get vehice detail list
    Given the login API is available and add headers for Invalid specific appid in get vehicle list
    When user add request Body for valid Empty Request Body for get vehice detail list
    And the user sends  get vehicle detail list request with "POST"
    Then Validate response for Empty request Body for get vehice detail list status should be responseCode 500

  @SingleVinTag
  Scenario: Successful login with valid CustomerIdentifier has single VIN tagged with a mobile number
    Given the login API is available and add headers for get vehicle detail list
    When user add requestBody for get vin detail list for single VIN tagged with a mobile number "9033356956" ,"mobile" and "sms"
    And the user sends a get vin detail list Otp request with "POST"
    Then the response status for get vin detail list for single VIN tagged with a mobile number should be 200 and message "OTP Generated Successfully" and loginId "9033356956"
    When user add request for get vehicle detail list for single VIN tagged with a mobile number along with loginId "9033356956" , isdCode "+91" ,deviceType "IOS" ,osVersion "15.1" ,appVersion "10" and deviceModel "x86_64"
    And the user sends a get vehicle detail list request with "POST"
    Then Validate the otp Generated for get vehicle detail list having the response code as 200 and verify message "OTP Verified Successfully"
    Given the login API is available and add headers and access token for get vehicle detail list
    When user add requestBody with valid customer Identifier for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for get vin detail list Otp should be 200 and message "Vehicle Details" and assestId1 "2-11Q73AV9"

  @TwoVinTag
  Scenario: Successful login with valid CustomerIdentifier has double VIN tagged with a mobile number
    Given the login API is available and add headers for get vehicle detail list
    When user add requestBody for get vin detail list for double VIN tagged with a mobile number "9891803084" ,"mobile" and "sms"
    And the user sends a get vin detail list Otp request with "POST"
    Then the response status for get vin detail list for double VIN tagged with a mobile number should be 200 and message "OTP Generated Successfully" and loginId "9891803084"
    When user add request for get vehicle detail list for double VIN tagged with a mobile number along with loginId "9891803084" , isdCode "+91" ,deviceType "IOS" ,osVersion "15.1" ,appVersion "10" and deviceModel "x86_64"
    And the user sends a get vehicle detail list request with "POST"
    Then Validate the otp Generated for get vehicle detail list having the response code as 200 and verify message "OTP Verified Successfully"
    Given the login API is available and add headers and access token for get vehicle detail list
    When user add requestBody with valid customer Identifier for get vehicle detail list
    And the user sends  get vehicle detail list request with "POST"
    Then the response status for get vin detail list Otp should be 200 and message "Vehicle Details" and assestId1 "2-1054VT3I" , assestId2 "2-1045LRXH"
    
    
  @Invalid_key
  Scenario: Successful login with valid mobile number and Invalid_ocp-apim-subscription-key in get vehicle list
    Given the login API is available and add headers for Invalid_ocp-apim-subscription-key in get vehicle list
    When user add requestBody with valid customer Identifier for Invalid_ocp-apim-subscription-key in get vehicle list
    And the user sends get vehicle detail list for Invalid_ocp-apim-subscription-key request with "POST"
    Then the response status for Invalid_ocp-apim-subscription-key in get vehicle list Otp should be 401 and message "Access denied due to invalid subscription key. Make sure to provide a valid key for an active subscription."

    
