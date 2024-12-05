Feature: Harley Enterprise : API Login Testing

  Background:
    Given the login API is available and user add headers

  Scenario Outline: Successful login with valid mobile number
    When user add requestBody for validLogin "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a ExternalLogin request with "POST"
    Then the response status should be 200 and message "OTP Generated Successfully" and loginId "9905662637"

    Examples:
      | loginId    | loginType | notificationType |
      | 9905662637 | mobile    | sms              |

  Scenario Outline: UnSuccessful login with Invalid mobile number
    When user add requestBody for InvalidMobileNumber "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a InvalidMobileNumber request with "POST"
    Then the response status should be 200 and message "User is not registered" , errorCode 1001 and errorDescription "Unable to process Your Request"

    Examples:
      | loginId    | loginType | notificationType |
      | 7004813883 | mobile    | sms              |

  Scenario Outline: UnSuccessful login with InvalidType mobile number
    When user add requestBody for Invalid Special Character in MobileNumber "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a Invalid Special Character in MobileNumber request with "POST"
    Then the response status should be 200 and the message "Mobile number is not numeric." , errorCode 1002 and errorDescription "Unable to process Your Request"

    Examples:
      | loginId    | loginType | notificationType |
      | 7004813&%3 | mobile    | sms              |

  Scenario Outline: UnSuccessful login with more than 10 digits in LoginId mobile number
    When user add requestBody for more than ten digits in MobileNumber "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a more than ten digits in MobileNumber request with "POST"
    Then the response status should be 200 and verify the message "Mobile Field Blank Or Not In Format." , errorCode 1002 and errorDescription "Unable to process Your Request"

    Examples:
      | loginId      | loginType | notificationType |
      | 700481300003 | mobile    | sms              |

  Scenario Outline: Successful login with valid mobile number and OTP generated Successfully
    When user add requestBody for validMobile Number for Otp generation "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a validMobile Number for Otp generation request with "POST"
    Then  response status should be 200 and message "OTP Generated Successfully" and loginId "9905662637"

    Examples:
      | loginId    | loginType | notificationType |
      | 9905662637 | mobile    | sms              |

  Scenario Outline: Unsuccessful login with special character in Invalid login Type
    When user add requestBody for login "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request with "POST"
    Then Validate response with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType   | notificationType | message                              | statusCode | success | errorCode | desc                           |
      | 9891803084 | $##$%#$%dwd | sms              | Invalid loginType, Please try again. | 200        | false   | 1002      | Unable to process Your Request |


  Scenario Outline: UnSuccessful login with Blank mobile type
    When user add requestBody for Blank mobile type "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a Blank mobile type request with "POST"
    Then the response status should be 200 for Blank mobile type request and the message "Invalid loginType, Please try again." , errorCode 1002 and errorDescription "Unable to process Your Request"

    Examples:
      | loginId    | loginType | notificationType |
      | 9891803084 |           | sms              |


  Scenario Outline: Unsuccessful login with More character in login Type
    When user add requestBody for login with More character in login Type "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a request for login with More character in login Type with "POST"
    Then Validate response for More character in login Type with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType   | notificationType | message                              | statusCode | success | errorCode | desc                           |
      | 9891803084 | mobile sk@# | sms              | Invalid loginType, Please try again. | 200        | false   | 1002      | Unable to process Your Request |

  Scenario Outline: Successful login with valid new mobile number
    When user add requestBody for valid new mobile number "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a valid new mobile number request with "POST"
    Then Validate response for new mobile number status should be 200 and message "OTP Generated Successfully" and loginId "8989457091"

    Examples:
      | loginId    | loginType | notificationType |
      | 8989457091 | mobile    | sms              |

  Scenario Outline: UnSuccessful login with Empty Request Body
    When user add requestBody for valid Empty Request Body
    And the user sends a Empty Request Body request with "POST"
    Then Validate response for Empty Request Body status should be "<responseCode>"

    Examples:
      | responseCode |
      | 500          |

  Scenario: Validate login attempts and account blocking behavior
    Given the login API is available and add header for multiple attempt Login
    When the user sends 5 valid login requests for "9891803084", "mobile", and "sms"
    Then the response status for all 5 requests should be 200 with message "OTP Generated Successfully"
    When the user sends the 6th login request for "9891803084", "mobile", and "sms"
    Then the response status should be 200 with message "Your account has been blocked for 15 minutes. Please try after 15 minutes "


  Scenario Outline: Unsuccessful login with  Invalid AppId in header
    Given the login API is available and add header for Invalid AppId
    When user add requestBody for Invalid AppId "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Invalid AppId with "POST"
    Then Validate response for Invalid AppId with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType | notificationType | message        | statusCode | success | errorCode | desc                           |
      | 7898325717 | mobile    | sms              | Invalid AppId. | 200        | false   | 1002      | Unable to process Your Request |


  Scenario Outline: Unsuccessful login with Blank AppId in header
    Given the login API is available and add header for Blank AppId
    When user add requestBody for Blank AppId "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Blank AppId with "POST"
    Then Validate response for Blank AppId with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType | notificationType | message        | statusCode | success | errorCode | desc                           |
      | 7898325717 | mobile    | sms              | Invalid AppId. | 200        | false   | 1002      | Unable to process Your Request |


  Scenario Outline: Unsuccessful login with Invalid Specific AppId in header
    Given the login API is available and add header for Invalid Specific AppId
    When user add requestBody for Invalid Specific AppId "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Invalid Specific AppId with "POST"
    Then Validate response for Invalid Specific AppId with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType | notificationType | message                 | statusCode | success | errorCode | desc                           |
      | 7898325717 | mobile    | sms              | Invalid Specific AppId. | 200        | false   | 1002      | Unable to process Your Request |


  Scenario Outline: Unsuccessful login with Blank Specific AppId in header
    Given the login API is available and add header for Blank Specific AppId
    When user add requestBody for Blank Specific AppId "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Blank Specific AppId with "POST"
    Then Validate response for Blank Specific AppId with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples:
      | loginId    | loginType | notificationType | message                 | statusCode | success | errorCode | desc                           |
      | 7898325717 | mobile    | sms              | Invalid Specific AppId. | 200        | false   | 1002      | Unable to process Your Request |

  Scenario Outline: Send API request with Invalid Json payload
    When the request payload is
    """
  <payload>
  """
    And user send a POST request "POST"
    Then the response status code should be <statusCode>

    Examples:
      | payload                                                                                     | statusCode |
      | { "data": { "loginId": "8989457091", "loginType": "mobile", "notificationType": "sms",, } } | 200        |

