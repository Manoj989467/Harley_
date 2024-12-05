package stepDefinition;

import baseClass.BaseClass;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import payloadManager.PayloadManager;
import pojoClass_ExternalLogin.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Tc1_External_Login extends BaseClass {

    PayloadManager payloadManager = new PayloadManager();
    @Given("the login API is available and user add headers")
    public void the_login_api_is_available_and_user_add_headers() {
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
    @When("user add requestBody for validLogin {string} ,{string} and {string}")
    public void user_add_requestBody_for_validLogin_and(String loginId, String loginType, String notification) throws IOException {
        
    	addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));   	
    	ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }
    @When("the user sends a ExternalLogin request with {string}")
    public void the_user_sends_a_externalLogin_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
      
    }
    @Then("the response status should be {int} and message {string} and loginId {string}")
    public void the_response_status_should_be_and_message_and_login_id(int expectedResponseCode, String expectedMsg, String expLoginID) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);
      
  
        assertEquals(expectedMsg, dataExternalLoginOutput.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(dataExternalLoginOutput.data.loginId, expLoginID, "verify LoginId");
    }

// <-------------------------------------------- Test 1 ------------------------------------------------------------->

    @When("user add requestBody for InvalidMobileNumber {string} ,{string} and {string}")
    public void user_add_requestBody_for_invalid_mobile_number_and(String loginId, String loginType, String notificationType) throws IOException {
        addReqBody(payloadManager.addGetExternalNonExistingMobileNumberPayload().addLoginDetails(loginId, loginType, notificationType));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notificationType);
    }
    @When("the user sends a InvalidMobileNumber request with {string}")
    public void the_user_sends_a_invalid_mobile_number_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }
    @Then("the response status should be {int} and message {string} , errorCode {int} and errorDescription {string}")
    public void the_response_status_should_be_and_message_error_code_and_error_description(int expectedResponseCode, String expectedMsg, Integer expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_NonExisting_MobNumber_Output_Pojo rootExternalLoginNonExistingMobNumberOutputPojo = getbody().as(Root_ExternalLogin_NonExisting_MobNumber_Output_Pojo.class);
        assertEquals(rootExternalLoginNonExistingMobNumberOutputPojo.message, expectedMsg, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode,rootExternalLoginNonExistingMobNumberOutputPojo.errorCode, "verifyErrorCode");
        assertEquals(expErrorDescription,rootExternalLoginNonExistingMobNumberOutputPojo.errorDescription,"verify error description");
    }
// <-------------------------------------------- Test 2 ------------------------------------------------------------->

    @When("user add requestBody for Invalid Special Character in MobileNumber {string} ,{string} and {string}")
    public void user_add_requestBody_for_invalid_special_character_in_mobile_number_and(String loginId, String loginType, String notification) throws IOException {

        addReqBody(payloadManager.addSpecialCharLoginIdPayLoad().addSpecialCharLoginIdPayLoad(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a Invalid Special Character in MobileNumber request with {string}")
    public void the_user_sends_a_invalid_special_character_in_mobile_number_request_with(String post) throws IOException {

        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("the response status should be {int} and the message {string} , errorCode {int} and errorDescription {string}")
    public void the_response_status_should_be_and_the_message_error_code_and_error_description(int expectedResponseCode, String expectedMsg, Integer expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_InValidMobNumber_Output_Pojo root_externalLogin_inValidMobNumber_output_pojo = getbody().as(Root_ExternalLogin_InValidMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, root_externalLogin_inValidMobNumber_output_pojo.message, "verify message");
        assertEquals(expectedResponseCode,getResponseCode(), "verify response code");
        assertEquals(expErrorCode, root_externalLogin_inValidMobNumber_output_pojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, root_externalLogin_inValidMobNumber_output_pojo.errorDescription, "verify Error Description");
    }
// <-------------------------------------------- Test 3 ------------------------------------------------------------->

    @When("user add requestBody for more than ten digits in MobileNumber {string} ,{string} and {string}")
    public void user_add_requestBody_for_more_than_ten_digits_in_mobile_number_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginMoreThan10DigitMobNumberPayload().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }
    @When("the user sends a more than ten digits in MobileNumber request with {string}")
    public void the_user_sends_a_more_than_ten_digits_in_mobile_number_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }
    @Then("the response status should be {int} and verify the message {string} , errorCode {int} and errorDescription {string}")
    public void the_response_status_should_be_and_verify_the_message_error_code_and_error_description(int expectedResponseCode, String expectedMsg, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
    }
// <-------------------------------------------- Test 4 ------------------------------------------------------------->

    @When("user add requestBody for validMobile Number for Otp generation {string} ,{string} and {string}")
    public void user_add_requestBody_for_valid_mobile_number_for_otp_generation_and(String loginId, String loginType, String notification) throws IOException {

        addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a validMobile Number for Otp generation request with {string}")
    public void the_user_sends_a_valid_mobile_number_for_otp_generation_request_with(String post) throws IOException {

        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("response status should be {int} and message {string} and loginId {string}")
    public void response_status_should_be_and_message_and_login_id(int expectedResponseCode, String expectedMsg, String expLoginId) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

        Root_ExternalLogin_Output_Pojo rootExternalLoginOutputPojo = getbody().as(Root_ExternalLogin_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expLoginId,rootExternalLoginOutputPojo.data.loginId,"verify login Id");
    }
// <-------------------------------------------- Test 5 ------------------------------------------------------------->

    @When("user add requestBody for login {string} ,{string} and {string}")
    public void user_add_requestBody_for_login_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.addInValid_LoginInput_PayLoad().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Base URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a login request with {string}")
    public void the_user_sends_a_login_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedStatusCode, String expectedSuccessMsg, Integer expectedErrorCode, String expectedDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Data_InvalidLogin_Output dataLoginOutput = getbody().as(Data_InvalidLogin_Output.class);
        assertEquals(expectedMsg, dataLoginOutput.message, "verify message");
        assertEquals(expectedStatusCode, getResponseCode(), "verify response code");
        assertEquals(expectedErrorCode, dataLoginOutput.errorCode, "verify error code");
        assertEquals(expectedDescription, dataLoginOutput.errorDescription, "verify error description");
    }
// <-------------------------------------------- Test 6 ------------------------------------------------------------->

    @When("user add requestBody for Blank mobile type {string} ,{string} and {string}")
    public void user_add_requestBody_for_blank_mobile_type_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginBlankInputTypePayload().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a Blank mobile type request with {string}")
    public void the_user_sends_a_blank_mobile_type_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("the response status should be {int} for Blank mobile type request and the message {string} , errorCode {int} and errorDescription {string}")
    public void the_response_status_should_be_for_blank_mobile_type_request_and_the_message_error_code_and_error_description(int expectedResponseCode, String expectedMsg, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Data_InvalidLogin_Output dataLoginOutput = getbody().as(Data_InvalidLogin_Output.class);
        assertEquals(expectedMsg, dataLoginOutput.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, dataLoginOutput.errorCode, "verify error code");
        assertEquals(expErrorDescription, dataLoginOutput.errorDescription, "verify error description");
    }
// <-------------------------------------------- Test 7 ------------------------------------------------------------->

    @When("user add requestBody for login with More character in login Type {string} ,{string} and {string}")
    public void user_add_requestBody_for_login_with_more_character_in_login_type_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginTypeMaximumCharPayload().addDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Base URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a request for login with More character in login Type with {string}")
    public void the_user_sends_a_request_for_login_with_more_character_in_login_type_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for More character in login Type with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_more_character_in_login_type_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedStatusCode, String expBoolean, int expectedErrorCode, String expectedDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Data_InvalidLogin_Output dataLoginOutput = getbody().as(Data_InvalidLogin_Output.class);
        assertEquals(expectedMsg, dataLoginOutput.message, "verify message");
        assertEquals(expectedStatusCode, getResponseCode(), "verify response code");
        assertEquals(expectedErrorCode, dataLoginOutput.errorCode, "verify error code");
        assertEquals(expectedDescription, dataLoginOutput.errorDescription, "verify error description");
        assertFalse(dataLoginOutput.success, "verify boolean message");
    }
// <-------------------------------------------- Test 8 ------------------------------------------------------------->

    @When("user add requestBody for valid new mobile number {string} ,{string} and {string}")
    public void user_add_requestBody_for_valid_new_mobile_number_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginValidNewMobileNumberPayLoad().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a valid new mobile number request with {string}")
    public void the_user_sends_a_valid_new_mobile_number_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for new mobile number status should be {int} and message {string} and loginId {string}")
    public void validate_response_for_new_mobile_number_status_should_be_and_message_and_login_id(int expectedResponseCode, String expectedMsg, String expLoginID) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_Login_NewMobileNumber_Output_Pojo rootLoginNewMobileNumberOutputPojo = getbody().as(Root_Login_NewMobileNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootLoginNewMobileNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expLoginID, rootLoginNewMobileNumberOutputPojo.data.loginId, "verify LoginId");
    }
// <-------------------------------------------- Test 9 ------------------------------------------------------------->

    @When("user add requestBody for valid Empty Request Body")
    public void user_add_requestBody_for_valid_empty_request_body() throws IOException {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: Empty");
    }

    @When("the user sends a Empty Request Body request with {string}")
    public void the_user_sends_a_empty_request_body_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Empty Request Body status should be {string}")
    public void validate_response_for_Empty_Request_Body_status_should_be(String expectedResponseCode) {
        int responseCode = getResponseCode();
        String actualResponse = Integer.toString(responseCode);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + responseCode);
        assertEquals(expectedResponseCode, actualResponse, "verify response code");
    }
// <-------------------------------------------- Test 10 ------------------------------------------------------------->

    @Given("the login API is available and add header for multiple attempt Login")
    public void the_login_api_is_available_and_add_header_for_multiple_attempt_login() {
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

    @When("the user sends {int} valid login requests for {string}, {string}, and {string}")
    public void sendValidLoginRequests(int attempts, String loginId, String loginType, String notificationType) throws IOException {
        for (int i = 0; i < attempts; i++) {
            Object payload = payloadManager
                    .getLoginMultipleAttemptPayLoad()
                    .addLoginDetails(loginId, loginType, notificationType);
            addReqBody(payload);
            addReqType("POST", getPropertyFileValue("Login"));

        }
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notificationType);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
    }

    @Then("the response status for all {int} requests should be {int} with message {string}")
    public void validateSuccessfulLoginResponses(int attempts, int expectedStatus, String expectedMessage)  {

        Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);

        for (int i = 0; i < attempts; i++) {
            assertEquals(expectedStatus, getResponseCode(), "verify responseCode");
            assertEquals(expectedMessage, dataExternalLoginOutput.message, "verify message");
        }
    }
// <-------------------------------------------- Test 11 ------------------------------------------------------------->

    Response response;
    @When("the user sends the 6th login request for {string}, {string}, and {string}")
    public void sendSixthLoginRequest(String loginId, String loginType, String notificationType) throws IOException {

        addReqBody(payloadManager.getLoginMultipleAttemptPayLoad().addLoginDetails(loginId, loginType, notificationType));
        response = addReqType("POST", getPropertyFileValue("Login"));

    }

    @Then("the response status should be {int} with message {string}")
    public void validateBlockedAccountResponse(int expectedStatus, String expectedMessage) {

        String message = response.jsonPath().get("message").toString();
        assertEquals(expectedStatus, getResponseCode(), "Status code 6th attempt");
        assertEquals(message, expectedMessage, "verify message");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

    }
// <-------------------------------------------- Test 12 ------------------------------------------------------------->

    @Given("the login API is available and add header for Invalid AppId")
    public void the_login_api_is_available_and_add_header_for_invalid_app_id() {
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

    @When("user add requestBody for Invalid AppId {string} ,{string} and {string}")
    public void user_add_request_body_for_invalid_app_id_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginInvalidAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a login request for Invalid AppId with {string}")
    public void the_user_sends_a_login_request_for_invalid_app_id_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Invalid AppId with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_invalid_app_id_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedResponseCode, String expBoolean, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
    }
// <-------------------------------------------- Test 13 ------------------------------------------------------------->

    @Given("the login API is available and add header for Blank AppId")
    public void the_login_api_is_available_and_add_header_for_blank_app_id() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        List<Header> list_headers = new ArrayList<>();
        Header h1 = new Header("appid", "");
        Header h2 = new Header("specificAppId", "Harley");
        Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
        list_headers.add(h1);
        list_headers.add(h2);
        list_headers.add(h3);
        Headers headers = new Headers(list_headers);
        addHeaders(headers);
    }

    @When("user add requestBody for Blank AppId {string} ,{string} and {string}")
    public void user_add_request_body_for_blank_app_id_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginBlankAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a login request for Blank AppId with {string}")
    public void the_user_sends_a_login_request_for_blank_app_id_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Blank AppId with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_blank_app_id_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedResponseCode, String expBoolean, Integer expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
    }
// <-------------------------------------------- Test 14 ------------------------------------------------------------->

    @Given("the login API is available and add header for Invalid Specific AppId")
    public void the_login_api_is_available_and_add_header_for_invalid_specific_app_id() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        List<Header> list_headers = new ArrayList<>();
        Header h1 = new Header("appid", "External");
        Header h2 = new Header("specificAppId", "HD X440");
        Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
        list_headers.add(h1);
        list_headers.add(h2);
        list_headers.add(h3);
        Headers headers = new Headers(list_headers);
        addHeaders(headers);
    }

    @When("user add requestBody for Invalid Specific AppId {string} ,{string} and {string}")
    public void user_add_request_body_for_invalid_specific_app_id_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginInvalidSpecialAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a login request for Invalid Specific AppId with {string}")
    public void the_user_sends_a_login_request_for_invalid_specific_app_id_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Invalid Specific AppId with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_invalid_specific_app_id_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedResponseCode, String expBoolean, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
        assertFalse(rootExternalLoginMoreThan10DigitMobNumberOutputPojo.success, "verify Boolean value");
    }
// <-------------------------------------------- Test 15 ------------------------------------------------------------->

    @Given("the login API is available and add header for Blank Specific AppId")
    public void the_login_api_is_available_and_add_header_for_blank_specific_app_id() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        List<Header> list_headers = new ArrayList<>();
        Header h1 = new Header("appid", "External");
        Header h2 = new Header("specificAppId", "");
        Header h3 = new Header("ocp-apim-subscription-key", "2b6bbec34ce14a32b968411d7956e24c");
        list_headers.add(h1);
        list_headers.add(h2);
        list_headers.add(h3);
        Headers headers = new Headers(list_headers);
        addHeaders(headers);
    }

    @When("user add requestBody for Blank Specific AppId {string} ,{string} and {string}")
    public void user_add_request_body_for_blank_specific_app_id_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getLoginBlankSpecialAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);

    }

    @When("the user sends a login request for Blank Specific AppId with {string}")
    public void the_user_sends_a_login_request_for_blank_specific_app_id_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Blank Specific AppId with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_blank_specific_app_id_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedResponseCode, String expBoolean, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
        assertFalse(rootExternalLoginMoreThan10DigitMobNumberOutputPojo.success, "verify Boolean value");
    }

// <-------------------------------------------- Test 16 ------------------------------------------------------------->

    @When("the request payload is")
    public void the_request_payload_is(String payload) throws IOException {
        reqspec.body(payload);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: " + payload);
    }
    @When("user send a POST request {string}")
    public void user_send_a_post_request(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expResponseCode) {
        assertEquals(expResponseCode, getResponseCode(), "verify responseCode");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + expResponseCode);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response_Body: " + getbody().asPrettyString());
    }
}

// <-------------------------------------------- Test 17 ------------------------------------------------------------->


