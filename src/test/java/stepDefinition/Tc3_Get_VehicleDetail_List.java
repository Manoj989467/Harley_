package stepDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import baseClass.BaseClass;
import global_Datas.Global_Datas;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import payloadManager.PayloadManager;
import pojoClass_ExternalLogin.Root_ExternalLogin_Output_Pojo;
import pojo_Class_GetVehicle_List.Blank_Customer_Identifier_Root_Output;
import pojo_Class_GetVehicle_List.Blank_Token_Root_Output;
import pojo_Class_GetVehicle_List.Invalid_Appid_Root_Ouput;
import pojo_Class_GetVehicle_List.Invalid_Customer_identifier_Root_Ouput;
import pojo_Class_GetVehicle_List.Invalid_Key_Root_Output;
import pojo_Class_GetVehicle_List.Valid_Customer_Identifier_Root_Output;
import pojo_Class_GetVehicle_List.VehicleDetail;
import pojo_External_Login_Otp.Verify_Otp_Tc2_Root_Output_Pojo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tc3_Get_VehicleDetail_List extends BaseClass {

	PayloadManager payloadManager = new PayloadManager();

	static Global_Datas datas = new Global_Datas();

	@Given("the login API is available and add headers for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@When("user add requestBody for get vin detail list {string} ,{string} and {string}")
	public void user_add_request_body_for_get_vin_detail_list_and(String loginId, String loginType, String notification)
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
	}

	@When("the user sends a get vin detail list Otp request with {string}")
	public void the_user_sends_a_get_vin_detail_list_Otp_request_with(String post)
			throws FileNotFoundException, IOException {

		addReqType(post, getPropertyFileValue("Login"));
	}

	@Then("the response status for get vin detail list Otp should be {int} and message {string} and loginId {string}")
	public void the_response_status_for_get_vin_detail_list_Otp_should_be_and_message_and_login_id(
			int expectedResponseCode, String expectedMsg, String expLoginID) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);
		assertEquals(expectedMsg, dataExternalLoginOutput.message, "verify message");
		assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
		assertEquals(dataExternalLoginOutput.data.loginId, expLoginID, "verify LoginId");

	}

	@When("user add request for get vehicle detail list along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
	public void user_add_request_for_get_vehicle_detail_list_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(
			String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel)
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode,
				deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Login Otp URL is: " + getPropertyFileValue("LoginOtp"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion
						+ ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken"
						+ getPropertyFileValue("firebaseToken"));

	}

	@When("the user sends a get vehicle detail list request with {string}")
	public void the_user_sends_a_get_vehicle_detail_list_request_with(String post)
			throws FileNotFoundException, IOException {

		addReqType(post, getPropertyFileValue("LoginOtp"));
	}

	@Then("Validate the otp Generated for get vehicle detail list having the response code as {int} and verify message {string}")
	public void validate_the_otp_generated_for_get_vehicle_detail_list_having_the_response_code_as_and_verify_message(
			int expResponseCode, String expectedMessage) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody()
				.as(Verify_Otp_Tc2_Root_Output_Pojo.class);

		Tc3_Get_VehicleDetail_List.datas.setCustomer_Identifier(verifyOtpTc2RootOutputPojo.data.customerIdentifier);
		assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
		assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");

		Tc3_Get_VehicleDetail_List.datas.setAccessToken(verifyOtpTc2RootOutputPojo.data.accessToken);

	}

	@Given("the login API is available and add headers and access token for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_and_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", "Bearer " + Tc3_Get_VehicleDetail_List.datas.accessToken);
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@When("user add requestBody with valid customer Identifier for get vehicle detail list")
	public void user_add_request_body_with_valid_customer_identifier_for_get_vehicle_detail_list() {

		addReqBody(payloadManager.getValid_Customer_Identifier()
				.addDetails(Tc3_Get_VehicleDetail_List.datas.customer_Identifier));
	}

	@When("the user sends  get vehicle detail list request with {string}")
	public void the_user_sends_get_vehicle_detail_list_request_with(String post)
			throws FileNotFoundException, IOException {

		addReqType(post, getPropertyFileValue("GetvehicleDetails"));
		System.out.println(getbody().asPrettyString());

	}

	@Then("the response status for get vin detail list Otp should be {int} and message {string}")
	public void the_response_status_for_get_vin_detail_list_otp_should_be_and_message(int expResponseCode,
			String expMessage) throws ParseException, JsonMappingException, JsonProcessingException {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Valid_Customer_Identifier_Root_Output customer_Identifier_Root_Output = getbody()
				.as(Valid_Customer_Identifier_Root_Output.class);

		assertEquals(customer_Identifier_Root_Output.message, expMessage, "verify message");
		assertEquals(getResponseCode(), expResponseCode, "verify ResponseCode");

	}

	// <----------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for invalid customer identifier and access token for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_for_invalid_customer_identifier_and_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", getPropertyFileValue("accesstoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with Invalid customer Identifier for get vehicle detail list")
	public void user_add_request_body_with_invalid_customer_identifier_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

	}

	@When("the user sends a Invalid customer identifier for get vin detail list otp request with {string}")
	public void the_user_sends_a_invalid_customer_identifier_for_get_vin_detail_list_otp_request_with(String post)
			throws FileNotFoundException, IOException {
		addReqType(post, getPropertyFileValue("GetvehicleDetails"));
	}

	@Then("the response status for Invalid customer identifier for get vin detail list Otp should be {int} and message {string} and errorCode {int}")
	public void the_response_status_for_invalid_customer_identifier_for_get_vin_detail_list_Otp_should_be_and_message_and_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		System.out.println(getbody().asPrettyString());

		Invalid_Customer_identifier_Root_Ouput customer_identifier_Root_Ouput = getbody()
				.as(Invalid_Customer_identifier_Root_Ouput.class);
		System.out.println("ok");
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		System.out.println(customer_identifier_Root_Ouput.message);
		System.out.println(customer_identifier_Root_Ouput.errorCode);
		assertEquals(expMessage, customer_identifier_Root_Ouput.message, "verify message");
		assertEquals(expErrorCode, customer_identifier_Root_Ouput.errorCode, "verify Error code");
	}

// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid appid in get vehicle list")
	public void the_login_api_is_available_and_add_headers_for_invalid_appid_in_get_vehicle_list() {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External123");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@When("user add requestBody with valid customer Identifier for Invalid appid in get vehicle list")
	public void user_add_request_body_with_valid_customer_identifier_for_invalid_appid_in_get_vehicle_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("InvalidcustomerIdentifier"));
	}

	@Then("the response status for Invalid appid in get vehicle list Otp should be {int} and message {string} , errorcode {int} , errorDescription {string}")
	public void the_response_status_for_invalid_appid_in_get_vehicle_list_otp_should_be_and_message_errorcode_error_description(
			int expResponseCode, String expMessage, int expErrorcode, String expErrorDescription) {

		Invalid_Appid_Root_Ouput appid_Root_Ouput = getbody().as(Invalid_Appid_Root_Ouput.class);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");
		assertTrue(appid_Root_Ouput.message.contains(expMessage));
		assertEquals(appid_Root_Ouput.errorDescription, expErrorDescription, "verify ErrorDescription");
		assertEquals(appid_Root_Ouput.errorCode, expErrorcode, "verify Errorcode");

	}

// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid specific appid in get vehicle list")
	public void the_login_api_is_available_and_add_headers_for_invalid_specific_appid_in_get_vehicle_list() {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley123");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@When("user add requestBody with valid customer Identifier for Invalid specific appid in get vehicle list")
	public void user_add_request_body_with_valid_customer_identifier_for_invalid_specific_appid_in_get_vehicle_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("InvalidcustomerIdentifier"));

	}

	@Then("the response status for Invalid specific appid in get vehicle list Otp should be {int} and message {string} , errorcode {int} , errorDescription {string}")
	public void the_response_status_for_invalid_specific_appid_in_get_vehicle_list_otp_should_be_and_message_errorcode_error_description(
			int expResponseCode, String expMessage, int expErrorcode, String expErrorDescription) {

		Invalid_Appid_Root_Ouput appid_Root_Ouput = getbody().as(Invalid_Appid_Root_Ouput.class);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");
		assertTrue(appid_Root_Ouput.message.contains(expMessage));
		assertEquals(appid_Root_Ouput.errorDescription, expErrorDescription, "verify ErrorDescription");
		assertEquals(appid_Root_Ouput.errorCode, expErrorcode, "verify Errorcode");

	}
	// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid_ocp-apim-subscription-key in get vehicle list")
	public void the_login_api_is_available_and_add_headers_for_invalid_ocp_apim_subscription_key_in_get_vehicle_list() {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley123");
		Header h3 = new Header("ocp-apim-subscription-key", "31110036a520491dacc8a96ee18aaa618686");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with valid customer Identifier for Invalid_ocp-apim-subscription-key in get vehicle list")
	public void user_add_request_body_with_valid_customer_identifier_for_invalid_ocp_apim_subscription_key_in_get_vehicle_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("InvalidcustomerIdentifier"));

	}

	@When("the user sends get vehicle detail list for Invalid_ocp-apim-subscription-key request with {string}")
	public void the_user_sends_get_vehicle_detail_list_for_invalid_ocp_apim_subscription_key_request_with(String post)
			throws FileNotFoundException, IOException {
		addReqType(post, getPropertyFileValue("GetvehicleDetails"));
	}

	@Then("the response status for Invalid_ocp-apim-subscription-key in get vehicle list Otp should be {int} and message {string}")
	public void the_response_status_for_invalid_ocp_apim_subscription_key_in_get_vehicle_list_otp_should_be_and_message(
			int expResponseCode, String expMessage) {

		Invalid_Key_Root_Output invalid_Key_Root_Output = getbody().as(Invalid_Key_Root_Output.class);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());

		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");
		assertEquals(invalid_Key_Root_Output.message, expMessage, "verify Message");
		assertEquals(invalid_Key_Root_Output.statusCode, expResponseCode, "verify Status code");

	}

// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Blank customer identifier and access token for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_for_blank_customer_identifier_and_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", getPropertyFileValue("accesstoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@When("user add requestBody with Blank customer Identifier for get vehicle detail list")
	public void user_add_request_body_with_blank_customer_identifier_for_get_vehicle_detail_list() {

		addReqBody(payloadManager.getBlank_Customer_Payload().addDetails());

	}

	@Then("the response status for Blank customer identifier for get vin detail list Otp should be {int} and message {string} and errorCode {int}")
	public void the_response_status_for_blank_customer_identifier_for_get_vin_detail_list_otp_should_be_and_message_and_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");

		Blank_Customer_Identifier_Root_Output blank_Customer_Identifier_Root_Output = getbody()
				.as(Blank_Customer_Identifier_Root_Output.class);

		assertEquals(blank_Customer_Identifier_Root_Output.message, expMessage, "verify message");
		assertEquals(blank_Customer_Identifier_Root_Output.errorCode, expErrorCode, "verify errorCode");
	}

	// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Special_char_customer_Identifier in get vehicle list")
	public void the_login_api_is_available_and_add_headers_for_special_char_customer_identifier_in_get_vehicle_list()
			throws FileNotFoundException, IOException {
		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", getPropertyFileValue("accesstoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody for Special_char_customer_Identifier in get vehicle list")
	public void user_add_request_body_for_special_char_customer_identifier_in_get_vehicle_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("specialcharcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("specialcharcustomerIdentifier"));
	}

	@Then("the response status for Special_char_customer_Identifier in get vehicle list Otp should be {int} and message {string} , errorcode {int} , errorDescription {string}")
	public void the_response_status_for_special_char_customer_identifier_in_get_vehicle_list_otp_should_be_and_message_errorcode_error_description(
			int expResponseCode, String expMessage, int expErrorcode, String expErrorDescription) {

		Invalid_Appid_Root_Ouput appid_Root_Ouput = getbody().as(Invalid_Appid_Root_Ouput.class);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");
		assertEquals(appid_Root_Ouput.message, expMessage, "verify message");
		assertEquals(appid_Root_Ouput.errorDescription, expErrorDescription, "verify ErrorDescription");
		assertEquals(appid_Root_Ouput.errorCode, expErrorcode, "verify Errorcode");

	}

// <-------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Old access Token for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_for_old_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", getPropertyFileValue("accesstoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with Old access Token for get vehicle detail list")
	public void user_add_request_body_with_old_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("InvalidcustomerIdentifier"));

	}

	@Then("the response status for Old access Token for get vin detail list Otp should be {int} and message {string} and errorCode {int}")
	public void the_response_status_for_old_access_token_for_get_vin_detail_list_otp_should_be_and_message_and_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");

		Blank_Customer_Identifier_Root_Output blank_Customer_Identifier_Root_Output = getbody()
				.as(Blank_Customer_Identifier_Root_Output.class);

		assertEquals(blank_Customer_Identifier_Root_Output.message, expMessage, "verify message");
		assertEquals(blank_Customer_Identifier_Root_Output.errorCode, expErrorCode, "verify errorCode");

	}

	// <-------------------------------------------------------------------------------------------------------------------------------------->
	@Given("the login API is available and add headers for blank access Token for get vehicle detail list")
	public void the_login_api_is_available_and_add_headers_for_blank_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {
		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "External");
		Header h2 = new Header("specificAppId", "Harley");
		Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
		Header h4 = new Header("Authorization", "");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with blank access Token for get vehicle detail list")
	public void user_add_request_body_with_blank_access_token_for_get_vehicle_detail_list()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getInvalid_Customer_Identifier_Payload()
				.addDetails(getPropertyFileValue("InvalidcustomerIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"GetvinDetailList URL is: " + getPropertyFileValue("GetvehicleDetails"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: CustomerIdentifier=" + getPropertyFileValue("InvalidcustomerIdentifier"));

	}

	@Then("the response status for blank access Token for get vin detail list Otp should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_blank_access_token_for_get_vin_detail_list_otp_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Response Status code is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		Blank_Token_Root_Output blank_Token_Root_Output = getbody().as(Blank_Token_Root_Output.class);
		assertEquals(getResponseCode(), expResponseCode, "verify Responsecode");
		assertEquals(blank_Token_Root_Output.message, expMessage, "verify message");
		assertEquals(blank_Token_Root_Output.errorCode, expErrorCode, "verify errorCode");
		assertEquals(blank_Token_Root_Output.errorDescription, expErrorDescription, "verify errorDescription");
	}

	// <-------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add request Body for valid Empty Request Body for get vehice detail list")
	public void user_add_request_body_for_valid_empty_request_body_for_get_vehice_detail_list()
			throws FileNotFoundException, IOException {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: Empty");
	}

	@Then("Validate response for Empty request Body for get vehice detail list status should be responseCode {int}")
	public void validate_response_for_empty_request_body_for_get_vehice_detail_list_status_should_be_response_code(
			int expectedResponseCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		assertEquals(expectedResponseCode, getResponseCode(), "verify response code");

	}
// <-------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody for get vin detail list for single VIN tagged with a mobile number {string} ,{string} and {string}")
	public void user_add_request_body_for_get_vin_detail_list_for_single_vin_tagged_with_a_mobile_number_and(
			String loginId, String loginType, String notification) throws FileNotFoundException, IOException {
		addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);

	}

	@Then("the response status for get vin detail list for single VIN tagged with a mobile number should be {int} and message {string} and loginId {string}")
	public void the_response_status_for_get_vin_detail_list_for_single_vin_tagged_with_a_mobile_number_should_be_and_message_and_login_id(
			int expectedResponseCode, String expectedMsg, String expLoginID) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);
		assertEquals(expectedMsg, dataExternalLoginOutput.message, "verify message");
		assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
		assertEquals(dataExternalLoginOutput.data.loginId, expLoginID, "verify LoginId");

	}

	@When("user add request for get vehicle detail list for single VIN tagged with a mobile number along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
	public void user_add_request_for_get_vehicle_detail_list_for_single_vin_tagged_with_a_mobile_number_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(
			String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel)
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode,
				deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion
						+ ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken"
						+ getPropertyFileValue("firebaseToken"));

	}

	@Then("the response status for get vin detail list Otp should be {int} and message {string} and assestId1 {string}")
	public void the_response_status_for_get_vin_detail_list_otp_should_be_and_message_and_assest_id1(
			int expResponseCode, String expMessage, String expAssestId) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Valid_Customer_Identifier_Root_Output customer_Identifier_Root_Output = getbody()
				.as(Valid_Customer_Identifier_Root_Output.class);

		assertEquals(customer_Identifier_Root_Output.message, expMessage, "verify message");
		assertEquals(getResponseCode(), expResponseCode, "verify ResponseCode");
		System.out.print(customer_Identifier_Root_Output.data.vehicleDetails.get(0).getAssetId());
		assertEquals(customer_Identifier_Root_Output.data.vehicleDetails.get(0).getAssetId(), expAssestId,
				"verify assestId");

	}

	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody for get vin detail list for double VIN tagged with a mobile number {string} ,{string} and {string}")
	public void user_add_request_body_for_get_vin_detail_list_for_double_vin_tagged_with_a_mobile_number_and(
			String loginId, String loginType, String notification) throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
	}

	@When("user add request for get vehicle detail list for double VIN tagged with a mobile number along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
	public void user_add_request_for_get_vehicle_detail_list_for_double_vin_tagged_with_a_mobile_number_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(
			String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel)
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode,
				deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion
						+ ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken"
						+ getPropertyFileValue("firebaseToken"));
	}

	@Then("the response status for get vin detail list for double VIN tagged with a mobile number should be {int} and message {string} and loginId {string}")
	public void the_response_status_for_get_vin_detail_list_for_double_vin_tagged_with_a_mobile_number_should_be_and_message_and_login_id(
			int expectedResponseCode, String expectedMsg, String expLoginID) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);
		assertEquals(expectedMsg, dataExternalLoginOutput.message, "verify message");
		assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
		assertEquals(dataExternalLoginOutput.data.loginId, expLoginID, "verify LoginId");

	}

	@Then("the response status for get vin detail list Otp should be {int} and message {string} and assestId1 {string} , assestId2 {string}")
	public void the_response_status_for_get_vin_detail_list_otp_should_be_and_message_and_assest_id1_assest_id2(
			int expResponseCode, String expMessage, String expAssestId1, String expAssestId2) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Valid_Customer_Identifier_Root_Output customer_Identifier_Root_Output = getbody()
				.as(Valid_Customer_Identifier_Root_Output.class);

		assertEquals(customer_Identifier_Root_Output.message, expMessage, "verify message");
		assertEquals(getResponseCode(), expResponseCode, "verify ResponseCode");
		assertEquals(customer_Identifier_Root_Output.data.vehicleDetails.get(0).getAssetId(), expAssestId1,
				"verify assestId1");
		assertEquals(customer_Identifier_Root_Output.data.vehicleDetails.get(1).getAssetId(), expAssestId2,
				"verify assestId2");

	}

	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
}
