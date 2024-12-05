package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(dryRun = false, monochrome = true, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, glue = {
				"stepDefinition" }, features = {
						"src/test/resources/Features/External_Login_Otp.feature"},tags = "@Invalid_Otp_Block")
						//tags="@SpaceinbtwNickname")
				//tags ="@ValidVinId or @InValidVinId or @InvalidNickname or @BlankVinId or @specialcharVinId or @SpecialcharNickname or @SpaceinbtwNickname or @InvalidCustomerIdentifier or @SpecialCharCustomerIdentifier or @BlankIdentifier or @InvalidToken or @BlankToken or @InvalidUrl or @BlankRequestBody")
						
public class TestRunnerClass extends AbstractTestNGCucumberTests {

}

//<------------------------------------------------------------------------------------------------------->

//features
//features for login ={src/test/resources/Features/Login.feature}
//features for verify otp ={src/test/resources/Features/External_Login_Otp.feature}
//features for verify otp ={src/test/resources/Features/GetVinDetailList.feature}
//features for update primary vehicle ={src/test/resources/Features/Update_Primary_Vehicle.feature}
//features for update vehicle nickname ={src/test/resources/Features/Update_Vehicle_Nickname.feature}
//<-------------------------------------------------------------------------------------------------------->

//Harley Login Numbers :- 9905662637, 9891803084, 8989457091, 7898325717, 7542034443

//tags for Tc2_External_Login_Otp

//Test1 = {@SuccessfulLogin or @Valid_Otp}
//Test2 = {@SuccessfulLogin or @InValid_Otp_length}
//Test3 = {@SuccessfulLogin or @Invalid_SpecialChar_Otp }
//Test4 = {@SuccessfulLogin or @Wrong_Otp}
//Test5 = {@SuccessfulLogin or @Blank_Otp }
//Test6 = {@SuccessfulLogin or @Space_In_between_Otp }
//Test7 = {@Reuse_Otp}
//Test8 = {@SuccessfulLogin or @Invalid_deviceModel }
//Test9 = {@SuccessfulLogin or @Blank_deviceModel }
//Test10 = {@SuccessfulLogin or @ISD_code_specialChar }
//Test11 = {@SuccessfulLogin or @Blank_IsdCode  }
//Test12 = {@SuccessfulLogin or @DeviceTye_specialChar }
//Test13 = {@SuccessfulLogin or @DeviceType_With_Space_AlphaNumeric }
//Test14 = {@SuccessfulLogin or @OsVersion_specialChar }
//Test15 = {@SuccessfulLogin or @AlphaNumeric_SpecialChar_Token  }
//Test16 = {@SuccessfulLogin or @Blank_Token  }
//Test17 = {@SuccessfulLogin or @Empty_RequestBody }
//Test18 = {@SuccessfulLogin or @Non_registered_number }
//Test19 = {@SuccessfulLogin or @Invalid_Specific_AppId }
//Test20 = {@SuccessfulLogin or @Invalid_AppId }
//Test21 = {@SuccessfulLogin or @Invalid_Otp_Block } login number for this test : 8989457091
//Test22 = {@SuccessfulLogin or @IsExisting_Flag_1}
//Test23 = {@SuccessfulLogin or @IsExisting_Flag_0}

//<------------------------------------------------------------------------------------------------------->

//tags for getvinDetailList={@validCustomerIdentifier or @InvalidCustomerIdentifier or @BlankIdentifier or @Invalid_appid or @Invalid_Specificappid or @Invalid_key or @Special_char_customer_Identifier or @Old_Token or @Blank_Token or @Empty_Body or @SingleVinTag or @TwoVinTag}
//tags for update primary vehicle ={"@ValidVinId or @InValidVinId or @BlankVinId or @InvalidCustomerIdentifier or @BlankCustomerIdentifier or @SpecialCharCustomerIdentifier or @InvalidToken or @BlankToken or @InvalidUrl or @BlankRequestBody or @Invalid_appid or @Invalid_Specificappid or @Invalid_key}





