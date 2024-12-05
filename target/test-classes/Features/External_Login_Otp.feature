Feature: Harley Enterprise : API Login Testing

  Background: 
    Given the login API is available and add headers for External Login Otp

  @SuccessfulLogin
  Scenario Outline: Successful login with valid mobile number for valid otp
    When user add requestBody for valid Login otp "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a valid Login otp request with "POST"
    Then the response status for valid login otp should be 200 and message "OTP Generated Successfully" and loginId "9905662637"

    Examples: 
      | loginId    | loginType | notificationType |
      | 9905662637 | mobile    | sms              |

  @Valid_Otp
  Scenario Outline: Successful login with OTP verification
    When user add request for otpLogin Endpoint along with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a valid Login Otp request with "POST"
    Then Validate the OTP Generated having the response code as 200 and verify message "<message>" , loginId "<loginId>" , firstName "<firstName>" , lastName "<lastName>" , appName "<appName>" and primaryUser "<primaryUser>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firstName  | lastName | appName | primaryUser |
      | +91     | IOS        |      15.1 |        10 | x86_64      | OTP Verified Successfully | 9905662637 | MONU KUMAR | KUMAR    | Harley  | Y           |

  @InValid_Otp_length
  Scenario Outline: Successful login with Invalid OTP Length
    When user add request for Invalid OTP Length along with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Invalid OTP Length with "POST"
    Then Validate the Invalid OTP Length OTP Generated having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message             | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | Invalid OTP length. | Unable to process Your Request |

  @Invalid_SpecialChar_Otp
  Scenario Outline: Successful login with Special char in OTP
    When user add request for Special char in Otp with loginId "9905662637" ,Otp "123&^%$" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Special char in Otp with "POST"
    Then Validate the OTP Generated for Special char in OTP having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                            | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | OTP contains special character(s). | Unable to process Your Request |

  @Wrong_Otp
  Scenario Outline: Successful login with Wrong OTP
    When user add request for Wrong OTP with loginId "9905662637" ,Otp "345678" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Wrong OTP in Otp with "POST"
    Then Validate the OTP Generated for Wrong OTP in OTP having the response code as 200 and verify message "<message>" , errorCode 1005 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                      | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | Invalid OTP 4 attempts left. | Unable to process Your Request |

  @Blank_Otp
  Scenario Outline: Successful login with Blank OTP
    When user add request for Blank OTP with loginId "9905662637" ,Otp "" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Blank OTP in Otp with "POST"
    Then Validate the OTP Generated for Blank OTP in OTP having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                        | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | OTP must not be Empty or Null. | Unable to process Your Request |

  @Space_In_between_Otp
  Scenario Outline: Successful login with space in between in OTP
    When user add request for space in between in OTP with loginId "9905662637" ,Otp "12 456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for space in between in OTP with "POST"
    Then Validate the OTP Generated for space in between in OTP having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message      | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | Invalid OTP. | Unable to process Your Request |

  @Reuse_Otp
  Scenario Outline: Successful login with reusing the OTP
    When user add request for reusing the OTP with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for reusing the OTP with "POST"
    Then Validate the OTP Generated for reusing the otp having the response code as 200 and verify message "<message>" , errorCode 1004 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                                | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | Your OTP has expired, Please try again | Unable to process Your Request |

  @Invalid_deviceModel
  Scenario Outline: Successful login with Invalid device Model OTP verification
    When user add request for Invalid device Model in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Invalid device Model in Otp with "POST"
    Then Validate the OTP Generated for Invalid device Model in Otp having the response code as 200 and verify message "<message>" , loginId "<loginId>" , firstName "<firstName>" , lastName "<lastName>" , appName "<appName>" and primaryUser "<primaryUser>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firstName | lastName | appName | primaryUser |
      | +91     | IOS        |      15.1 |        10 | x86_64$#$   | OTP Verified Successfully | 9905662637 | VA        | R        | Harley  | Y           |

  @Blank_deviceModel
  Scenario Outline: Successful login with Invalid device Model OTP verification
    When user add request for Blank device Model in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Blank device Model in Otp with "POST"
    Then Validate the OTP Generated for Blank device Model in Otp having the response code as 200 and verify message "<message>" , loginId "<loginId>" , firstName "<firstName>" , lastName "<lastName>" , appName "<appName>" and primaryUser "<primaryUser>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firstName | lastName | appName | primaryUser |
      | +91     | IOS        |      15.1 |        10 |             | OTP Verified Successfully | 9905662637 | VA        | R        | Harley  | Y           |

  @ISD_code_specialChar
  Scenario Outline: Successful login with wrong ISD code with special char in OTP verification
    When user add request for wrong isd code in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for wrong isd code in Otp with "POST"
    Then Validate the OTP Generated for wrong isd code in otp having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                                | errorDescription               |
      | +91#$%  | IOS        |      15.1 |        10 | x86_64      | IsdCode contains special character(s). | Unable to process Your Request |

  @Blank_IsdCode
  Scenario Outline: Successful login with Blank IsdCode in OTP verification
    When user add request for Blank IsdCode in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Blank IsdCode in Otp with "POST"
    Then Validate the OTP Generated for Blank IsdCode in Otp having the response code as 200 and verify message "<message>" , loginId "<loginId>" , firstName "<firstName>" , lastName "<lastName>" , appName "<appName>" and primaryUser "<primaryUser>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firstName | lastName | appName | primaryUser |
      |         | IOS        |      15.1 |        10 | x86_64      | OTP Verified Successfully | 9905662637 | VA        | R        | Harley  | Y           |

  @DeviceTye_specialChar
  Scenario Outline: Successful login with Invalid device type with special char in OTP verification
    When user add request for Invalid device type with special char in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Invalid device type with special char in Otp with "POST"
    Then Validate the OTP Generated for Invalid device type with special char in otp having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                                   | errorDescription               |
      | +91     | #$IOS      |      15.1 |        10 | x86_64      | DeviceType contains special character(s). | Unable to process Your Request |

  @DeviceType_With_Space_AlphaNumeric
  Scenario Outline: Successful login with device type with space and alpha numeric in OTP verification
    When user add request for device type with space and alpha numeric in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for device type with space and alpha numeric in Otp with "POST"
    Then Validate the OTP Generated for device type with space and alpha numeric in Otp having the response code as 200 and verify message "<message>" , loginId "<loginId>" , firstName "<firstName>" , lastName "<lastName>" , appName "<appName>" and primaryUser "<primaryUser>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firstName | lastName | appName | primaryUser |
      |      91 | IOS 153    |      15.1 |        10 | x86_64      | OTP Verified Successfully | 9905662637 | VA        | R        | Harley  | Y           |

  @OsVersion_specialChar
  Scenario Outline: Successful login with Invalid OS version with special char in OTP verification
    When user add request for Invalid OS version with special char in Otp with loginId "9905662637" ,Otp "123456" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for Invalid OS version with special char in Otp with "POST"
    Then Validate the OTP Generated for Invalid OS version with special char in otp having the response code as 200 and verify message "<message>" , errorCode 1002 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                                  | errorDescription               |
      | +91     | IOS        | 15.1#$    |        10 | x86_64      | OsVersion contains special character(s). | Unable to process Your Request |

  @AlphaNumeric_SpecialChar_Token
  Scenario Outline: Successful login with alpha numeric special char in firebase token in OTP verification
    When user add request for alpha numeric special char in firebase token along with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for alpha numeric special char in firebase token in Otp with "POST"
    Then Validate the OTP Generated for alpha numeric special char in firebase token having the response code as 200 and verify message "<message>" , loginId "<loginId>" and appName "Harley"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    |
      | +91     | IOS        |      15.1 |        10 | x86_64      | OTP Verified Successfully | 9905662637 |

  @Blank_Token
  Scenario Outline: Successful login with blank token in firebase token in OTP verification
    When user add request for blank token in firebase token along with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" , deviceModel "<deviceModel>" and firebaseToken "<firebaseToken>"
    And the user sends a request for blank token  in firebase token in Otp with "POST"
    Then Validate the OTP Generated for blank token in firebase token having the response code as 200 and verify message "<message>" ,loginId "<loginId>" and appName "Harley"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                   | loginId    | firebaseToken |
      | +91     | IOS        |      15.1 |        10 | x86_64      | OTP Verified Successfully | 9905662637 |               |

  @Empty_RequestBody
  Scenario Outline: Successful login with Empty Request Body
    When user add request Body for valid Empty Request Body
    And the user sends a Empty request Body request with "POST"
    Then Validate response for Empty request Body status should be "<responseCode>"

    Examples: 
      | responseCode |
      |          500 |

  @Non_registered_number
  Scenario Outline: Successful login with non registered mobile number in OTP verification
    When user add request for non registered mobile number with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for non registered mobile number in Otp with "POST"
    Then Validate the OTP Generated for non registered mobile number in otp having the response code as 200 and verify message "<message>" , errorCode 1001 and errorDescription "<errorDescription>"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | message                | errorDescription               |
      | +91     | IOS        |      15.1 |        10 | x86_64      | User is not registered | Unable to process Your Request |

  @Invalid_Specific_AppId
  Scenario Outline: Unsuccessful login with Invalid Specific AppId in header for otp verification
    Given the login API is available and add header for Invalid Specific AppId for otp verification
    When user add requestBody for Invalid Specific AppId for otp verification "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Invalid Specific AppId for otp verification with "POST"
    Then Validate response for Invalid Specific AppId for otp verification with Message "<message>", Status code <statusCode> ,Success "<success>", errorCode <errorCode> and error description "<desc>"

    Examples: 
      | loginId    | loginType | notificationType | message                 | statusCode | success | errorCode | desc                           |
      | 7542034443 | mobile    | sms              | Invalid Specific AppId. |        200 | false   |      1002 | Unable to process Your Request |

  @Invalid_AppId
  Scenario Outline: Unsuccessful login with Invalid AppId in header for otp verification
    Given the login API is available and add header for Invalid AppId in otp verification
    When user add requestBody for Invalid AppId for otp verification "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a login request for Invalid AppId for otp verification with "POST"
    Then Validate response for Invalid AppId for otp verification with Message "<message>", Status code <statusCode> ,errorCode <errorCode> and error description "<desc>"

    Examples: 
      | loginId    | loginType | notificationType | message        | statusCode | errorCode | desc                           |
      | 7542034443 | mobile    | sms              | Invalid AppId. |        200 |      1002 | Unable to process Your Request |

  @Invalid_Otp_Block
  Scenario Outline: Validate login attempts and account blocking behavior
    When user add requestBody for valid Login otp "<loginId>" ,"<loginType>" and "<notificationType>"
    And the user sends a valid Login otp request with "POST"
    Then the response status for valid login otp should be 200 and message "OTP Generated Successfully" and loginId "9905662637"
    When the user sends 5 valid login requests with Invalid otp along with loginId "8989457091" , otp "123478" ,isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    Then the response status for Invalid otp should be 200 with message "Your account has been blocked for 15 minutes. Please try after 15 minutes ", errorDescription "<errorDescription>" and errorCode 1003

    Examples: 
      | loginId  loginType | notificationType | isdCode | deviceType | osVersion | apVersion | deviceModel | errorDescription               |
      | 8989457091 mobile  | sms              | +91     | IOS        |      15.1 |        10 | x86_64      | Unable to process Your Request |

  @IsExisting_Flag_1
  Scenario Outline: Successful login with OTP verification and verify IsExisting Flag should be 1 for existing user
    When user add request for IsExisting Flag along with loginId "9905662637" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    Then Validate the OTP Generated for IsExisting having the response code as 200 and verify IsExisting Flag is "1"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel |
      | +91     | IOS        |      15.1 |        10 | x86_64      |

  @IsExisting_Flag_0
  Scenario Outline: Successful login with OTP verification and verify IsExisting Flag should be 0 for existing user
    When user add requestBody for valid Login OTP "<loginId>" ,"<loginType>" and "<notificationType>"
    When user add request for IsExisting Flag is zero along with loginId "8219559673" , isdCode "<isdCode>" ,deviceType "<deviceType>" ,osVersion "<osVersion>" ,appVersion "<apVersion>" and deviceModel "<deviceModel>"
    And the user sends a request for IsExisting Flag in Otp with "POST"
    Then Validate the OTP Generated for IsExisting flag is zero having the response code as 200 and verify IsExisting Flag is "0"

    Examples: 
      | isdCode | deviceType | osVersion | apVersion | deviceModel | loginId    | loginType | notificationType |
      | +91     | IOS        |      15.1 |        10 | x86_64      | 8219559673 | mobile    | sms              |
