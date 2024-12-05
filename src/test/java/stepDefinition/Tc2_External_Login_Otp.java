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
import pojoClass_ExternalLogin.Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo;
import pojoClass_ExternalLogin.Root_ExternalLogin_Output_Pojo;
import pojo_External_Login_Otp.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Tc2_External_Login_Otp extends BaseClass {

    PayloadManager payloadManager = new PayloadManager();

    @Given("the login API is available and add headers for External Login Otp")
    public void the_login_api_is_available_and_add_headers_for_external_login_otp() {

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

    @When("user add requestBody for valid Login otp {string} ,{string} and {string}")
    public void user_add_request_body_for_valid_login_otp_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a valid Login otp request with {string}")
    public void the_user_sends_a_valid_login_otp_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("the response status for valid login otp should be {int} and message {string} and loginId {string}")
    public void the_response_status_for_valid_login_otp_should_be_and_message_and_login_id(int expectedResponseCode, String expectedMsg, String expLoginID) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_Output_Pojo dataExternalLoginOutput = getbody().as(Root_ExternalLogin_Output_Pojo.class);
        assertEquals(expectedMsg, dataExternalLoginOutput.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(dataExternalLoginOutput.data.loginId, expLoginID, "verify LoginId");
    }

    @When("user add request for otpLogin Endpoint along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_otp_login_endpoint_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a valid Login Otp request with {string}")
    public void the_user_sends_a_valid_login_Otp_request_with(String post) throws IOException {

        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated having the response code as {int} and verify message {string} , loginId {string} , firstName {string} , lastName {string} , appName {string} and primaryUser {string}")
    public void validate_the_otp_generated_having_the_response_code_as_and_verify_message_login_id_first_name_last_name_app_name_and_primary_user(int expResponseCode, String expectedMessage, String expectedLoginId, String expectedFirstName, String expectedLastName, String expectedAppName, String expectedPrimaryUser) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
        assertEquals(verifyOtpTc2RootOutputPojo.data.loginId, expectedLoginId, "verify loginId");
        assertEquals(verifyOtpTc2RootOutputPojo.data.firstName, expectedFirstName, "verify FirstName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.lastName, expectedLastName, "verify LastName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.appName, expectedAppName, "verify AppName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.primaryUser, expectedPrimaryUser, "verify getPrimaryUser");
    }
// <-------------------------------------------- Test 1 ------------------------------------------------------------->

    @When("user add request for Invalid OTP Length along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_invalid_otp_length_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, getPropertyFileValue("LengthOtp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Invalid OTP Length with {string}")
    public void the_user_sends_a_request_for_invalid_otp_length_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the Invalid OTP Length OTP Generated having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_invalid_otp_length_otp_generated_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }
// <-------------------------------------------- Test 2 ------------------------------------------------------------->

    @When("user add request for Special char in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_special_char_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Special char in Otp with {string}")
    public void the_user_sends_a_request_for_special_char_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for Special char in OTP having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_special_char_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }
// <-------------------------------------------- Test 3 ------------------------------------------------------------->

    @When("user add request for Wrong OTP with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_wrong_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Wrong OTP in Otp with {string}")
    public void the_user_sends_a_request_for_wrong_otp_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for Wrong OTP in OTP having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_wrong_otp_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }
// <-------------------------------------------- Test 4 ------------------------------------------------------------->

    @When("user add request for Blank OTP with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_blank_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));

    }

    @When("the user sends a request for Blank OTP in Otp with {string}")
    public void the_user_sends_a_request_for_blank_otp_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for Blank OTP in OTP having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_blank_otp_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }

// <-------------------------------------------- Test 5 ------------------------------------------------------------->

    @When("user add request for space in between in OTP with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_space_in_between_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for space in between in OTP with {string}")
    public void the_user_sends_a_request_for_space_in_between_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for space in between in OTP having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_space_in_between_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }

// <-------------------------------------------- Test 6 ------------------------------------------------------------->

    @When("user add request for reusing the OTP with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_reusing_the_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel
    ) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for reusing the OTP with {string}")
    public void the_user_sends_a_request_for_reusing_the_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for reusing the otp having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_reusing_the_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }

// <-------------------------------------------- Test 7 ------------------------------------------------------------->

    @When("user add request for Invalid device Model in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_invalid_device_model_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Invalid device Model in Otp with {string}")
    public void the_user_sends_a_request_for_invalid_device_model_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for Invalid device Model in Otp having the response code as {int} and verify message {string} , loginId {string} , firstName {string} , lastName {string} , appName {string} and primaryUser {string}")
    public void validate_the_otp_generated_for_invalid_device_model_in_otp_having_the_response_code_as_and_verify_message_login_id_first_name_last_name_app_name_and_primary_user(int expResponseCode, String expectedMessage, String expectedLoginId, String expectedFirstName, String expectedLastName, String expectedAppName, String expectedPrimaryUser) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
        assertEquals(verifyOtpTc2RootOutputPojo.data.loginId, expectedLoginId, "verify loginId");
        assertEquals(verifyOtpTc2RootOutputPojo.data.firstName, expectedFirstName, "verify FirstName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.lastName, expectedLastName, "verify LastName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.appName, expectedAppName, "verify AppName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.primaryUser, expectedPrimaryUser, "verify getPrimaryUser");
    }

// <-------------------------------------------- Test 8 ------------------------------------------------------------->

    @When("user add request for Blank device Model in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_blank_device_model_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Blank device Model in Otp with {string}")
    public void the_user_sends_a_request_for_blank_device_model_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for Blank device Model in Otp having the response code as {int} and verify message {string} , loginId {string} , firstName {string} , lastName {string} , appName {string} and primaryUser {string}")
    public void validate_the_otp_generated_for_blank_device_model_in_otp_having_the_response_code_as_and_verify_message_login_id_first_name_last_name_app_name_and_primary_user(int expResponseCode, String expectedMessage, String expectedLoginId, String expectedFirstName, String expectedLastName, String expectedAppName, String expectedPrimaryUser) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
        assertEquals(verifyOtpTc2RootOutputPojo.data.loginId, expectedLoginId, "verify loginId");
        assertEquals(verifyOtpTc2RootOutputPojo.data.firstName, expectedFirstName, "verify FirstName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.lastName, expectedLastName, "verify LastName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.appName, expectedAppName, "verify AppName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.primaryUser, expectedPrimaryUser, "verify getPrimaryUser");
    }
// <-------------------------------------------- Test 9 ------------------------------------------------------------->

    @When("user add request for wrong isd code in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_wrong_isd_code_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for wrong isd code in Otp with {string}")
    public void the_user_sends_a_request_for_wrong_isd_code_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for wrong isd code in otp having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_wrong_isd_code_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }
// <-------------------------------------------- Test 10 ------------------------------------------------------------->

    @When("user add request for Blank IsdCode in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_blank_isd_code_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Blank IsdCode in Otp with {string}")
    public void the_user_sends_a_request_for_blank_isd_code_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for Blank IsdCode in Otp having the response code as {int} and verify message {string} , loginId {string} , firstName {string} , lastName {string} , appName {string} and primaryUser {string}")
    public void validate_the_otp_generated_for_blank_isd_code_in_otp_having_the_response_code_as_and_verify_message_login_id_first_name_last_name_app_name_and_primary_user(int expResponseCode, String expectedMessage, String expectedLoginId, String expectedFirstName, String expectedLastName, String expectedAppName, String expectedPrimaryUser) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
        assertEquals(verifyOtpTc2RootOutputPojo.data.loginId, expectedLoginId, "verify loginId");
        assertEquals(verifyOtpTc2RootOutputPojo.data.firstName, expectedFirstName, "verify FirstName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.lastName, expectedLastName, "verify LastName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.appName, expectedAppName, "verify AppName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.primaryUser, expectedPrimaryUser, "verify getPrimaryUser");
    
    }

// <-------------------------------------------- Test 11 ------------------------------------------------------------->

    @When("user add request for Invalid device type with special char in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_invalid_device_type_with_special_char_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));

    }

    @When("the user sends a request for Invalid device type with special char in Otp with {string}")
    public void the_user_sends_a_request_for_invalid_device_type_with_special_char_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for Invalid device type with special char in otp having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_invalid_device_type_with_special_char_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }
// <-------------------------------------------- Test 12 ------------------------------------------------------------->


    @When("user add request for device type with space and alpha numeric in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_device_type_with_space_and_alpha_numeric_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for device type with space and alpha numeric in Otp with {string}")
    public void the_user_sends_a_request_for_device_type_with_space_and_alpha_numeric_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for device type with space and alpha numeric in Otp having the response code as {int} and verify message {string} , loginId {string} , firstName {string} , lastName {string} , appName {string} and primaryUser {string}")
    public void validate_the_otp_generated_for_device_type_with_space_and_alpha_numeric_in_otp_having_the_response_code_as_and_verify_message_login_id_first_name_last_name_app_name_and_primary_user(int expResponseCode, String expectedMessage, String expectedLoginId, String expectedFirstName, String expectedLastName, String expectedAppName, String expectedPrimaryUser) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2RootOutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(verifyOtpTc2RootOutputPojo.message, expectedMessage, "verify Msg");
        assertEquals(verifyOtpTc2RootOutputPojo.data.loginId, expectedLoginId, "verify loginId");
        assertEquals(verifyOtpTc2RootOutputPojo.data.firstName, expectedFirstName, "verify FirstName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.lastName, expectedLastName, "verify LastName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.appName, expectedAppName, "verify AppName");
        assertEquals(verifyOtpTc2RootOutputPojo.data.primaryUser, expectedPrimaryUser, "verify getPrimaryUser");
    }

// <-------------------------------------------- Test 12 ------------------------------------------------------------->

    @When("user add request for Invalid OS version with special char in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_invalid_os_version_with_special_char_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for Invalid OS version with special char in Otp with {string}")
    public void the_user_sends_a_request_for_invalid_os_version_with_special_char_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for Invalid OS version with special char in otp having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_invalid_os_version_with_special_char_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }

// <-------------------------------------------- Test 13 ------------------------------------------------------------->

    @When("user add request for alpha numeric special char in firebase token along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_alpha_numeric_special_char_in_firebase_token_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("specialCharFirebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("specialCharFirebaseToken"));
    }

    @When("the user sends a request for alpha numeric special char in firebase token in Otp with {string}")
    public void the_user_sends_a_request_for_alpha_numeric_special_char_in_firebase_token_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }


    @Then("Validate the OTP Generated for alpha numeric special char in firebase token having the response code as {int} and verify message {string} , loginId {string} and appName {string}")
    public void validate_the_otp_generated_for_alpha_numeric_special_char_in_firebase_token_having_the_response_code_as_and_verify_message_login_id_and_appName(int expResponseCode, String expMessage, String expLoginId, String expAppName) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Firebase_Token_Root_Output_Pojo verifyFirebaseTokenRootOutputPojo = getbody().as(Verify_Firebase_Token_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyFirebaseTokenRootOutputPojo.message, "verify Msg");
        assertEquals(expLoginId, verifyFirebaseTokenRootOutputPojo.data.loginId, "verify login Id");
        assertEquals(expAppName, verifyFirebaseTokenRootOutputPojo.data.appName, "verify app name");

    }

// <-------------------------------------------- Test 14 ------------------------------------------------------------->

    @When("user add request for blank token in firebase token along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} , deviceModel {string} and firebaseToken {string}")
    public void user_add_request_for_blank_token_in_firebase_token_along_with_login_id_isd_code_device_type_os_version_app_version_device_model_and_firebase_token(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel, String token) throws IOException {

        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, token));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + token);

    }

    @When("the user sends a request for blank token  in firebase token in Otp with {string}")
    public void the_user_sends_a_request_for_blank_token_in_firebase_token_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for blank token in firebase token having the response code as {int} and verify message {string} ,loginId {string} and appName {string}")
    public void validate_the_otp_generated_for_blank_token_in_firebase_token_having_the_response_code_as_and_verify_message_login_id_and_app_name(int expResponseCode, String expMessage, String expLoginId, String expAppName) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_Firebase_Token_Root_Output_Pojo verifyFirebaseTokenRootOutputPojo = getbody().as(Verify_Firebase_Token_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyFirebaseTokenRootOutputPojo.message, "verify Msg");
        assertEquals(expLoginId, verifyFirebaseTokenRootOutputPojo.data.loginId, "verify login Id");
        assertEquals(expAppName, verifyFirebaseTokenRootOutputPojo.data.appName, "verify app name");

    }

// <-------------------------------------------- Test 15 ------------------------------------------------------------->


    @When("user add request Body for valid Empty Request Body")
    public void user_add_request_body_for_valid_empty_request_body() throws IOException {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload is : Empty");

    }

    @When("the user sends a Empty request Body request with {string}")
    public void the_user_sends_a_empty_request_body_request_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate response for Empty request Body status should be {string}")
    public void validate_response_for_empty_request_body_status_should_be(String expResponseCode) {
        int responseCode = getResponseCode();
        String actualResponse = Integer.toString(responseCode);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        assertEquals(expResponseCode, actualResponse, "verify ResponseCode");
    }

// <-------------------------------------------- Test 16 ------------------------------------------------------------->

    @When("user add request for non registered mobile number with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_non_registered_mobile_number_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getOtpInvalidPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for non registered mobile number in Otp with {string}")
    public void the_user_sends_a_request_for_non_registered_mobile_number_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));

    }

    @Then("Validate the OTP Generated for non registered mobile number in otp having the response code as {int} and verify message {string} , errorCode {int} and errorDescription {string}")
    public void validate_the_otp_generated_for_non_registered_mobile_number_in_otp_having_the_response_code_as_and_verify_message_error_code_and_error_description(int expResponseCode, String expMessage, int expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify Msg");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");
    }

// <-------------------------------------------- Test 17 ------------------------------------------------------------->


    @Given("the login API is available and add header for Invalid Specific AppId for otp verification")
    public void the_login_api_is_available_and_add_header_for_invalid_specific_app_id_for_otp_verification() {

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

    @When("user add requestBody for Invalid Specific AppId for otp verification {string} ,{string} and {string}")
    public void user_add_request_body_for_invalid_specific_app_id_for_otp_verification_and(String loginId, String loginType, String notification) throws IOException {

        addReqBody(payloadManager.getLoginInvalidSpecialAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
    }

    @When("the user sends a login request for Invalid Specific AppId for otp verification with {string}")
    public void the_user_sends_a_login_request_for_invalid_specific_app_id_for_otp_verification_with(String post) throws IOException {

        addReqType(post, getPropertyFileValue("Login"));

    }

    @Then("Validate response for Invalid Specific AppId for otp verification with Message {string}, Status code {int} ,Success {string}, errorCode {int} and error description {string}")
    public void validate_response_for_invalid_specific_app_id_for_otp_verification_with_message_status_code_success_error_code_and_error_description(String expectedMsg, int expectedResponseCode, String expBoolean, Integer expErrorCode, String expErrorDescription) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
        assertFalse(rootExternalLoginMoreThan10DigitMobNumberOutputPojo.success, "verify Boolean value");
    }


// <-------------------------------------------- Test 18 ------------------------------------------------------------->

    @Given("the login API is available and add header for Invalid AppId in otp verification")
    public void the_login_api_is_available_and_add_header_for_invalid_app_id_in_otp_verification() {
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

    @When("user add requestBody for Invalid AppId for otp verification {string} ,{string} and {string}")
    public void user_add_request_body_for_invalid_app_id_for_otp_verification_and(String loginId, String loginType, String notification) throws IOException {

        addReqBody(payloadManager.getLoginInvalidAppIdPayLoad().adddetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);

    }

    @When("the user sends a login request for Invalid AppId for otp verification with {string}")
    public void the_user_sends_a_login_request_for_invalid_app_id_for_otp_verification_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("Login"));
    }

    @Then("Validate response for Invalid AppId for otp verification with Message {string}, Status code {int} ,errorCode {int} and error description {string}")
    public void validate_response_for_invalid_app_id_for_otp_verification_with_message_status_code_error_code_and_error_description(String expectedMsg, int expectedResponseCode, int expErrorCode, String expErrorDescription) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo rootExternalLoginMoreThan10DigitMobNumberOutputPojo = getbody().as(Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo.class);
        assertEquals(expectedMsg, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.message, "verify message");
        assertEquals(expectedResponseCode, getResponseCode(), "verify response code");
        assertEquals(expErrorCode, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorCode, "verify Error code");
        assertEquals(expErrorDescription, rootExternalLoginMoreThan10DigitMobNumberOutputPojo.errorDescription, "verify Error Description");
    }
// <-------------------------------------------- Test 19 ------------------------------------------------------------->

    @When("the user sends {int} valid login requests with Invalid otp along with loginId {string} , otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void the_user_sends_valid_login_requests_with_invalid_otp_along_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(int attempts, String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        for (int i = 0; i < attempts; i++) {
            Object payload = payloadManager
                    .getVerifyOtpPayload()
                    .addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken"));
            addReqBody(payload);
            Response response1 = addReqType("POST", getPropertyFileValue("LoginOtp"));
            System.out.println(response1.getBody().asPrettyString());
        }
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @Then("the response status for Invalid otp should be {int} with message {string}, errorDescription {string} and errorCode {int}")
    public void the_response_status_for_invalid_otp_should_be_with_message_error_description_and_error_code(int expResponseCode, String expMessage, String expErrorDescription, int expErrorCode) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Verify_InvalidOtp_Tc1_Root_Output_Pojo verifyInvalidOtpTc1RootOutputPojo = getbody().as(Verify_InvalidOtp_Tc1_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, verifyInvalidOtpTc1RootOutputPojo.message, "verify Msg");
        assertEquals(expErrorCode, verifyInvalidOtpTc1RootOutputPojo.errorCode, "verify error code");
        assertEquals(expErrorDescription, verifyInvalidOtpTc1RootOutputPojo.errorDescription, "verify Msg");

    }

// <-------------------------------------------- Test 20 ------------------------------------------------------------->

    @When("user add request for Invalid url in Otp with loginId {string} ,Otp {string} ,isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_invalid_url_in_otp_with_login_id_otp_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for device with Invalid url in Otp with {string}")
    public void the_user_sends_a_request_for_device_with_invalid_url_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("InvalidVerifyOtp"));
    }

    @Then("Validate for Invalid url in Otp having the response code as {int} and verify message {string}")
    public void validate_for_invalid_url_in_otp_having_the_response_code_as_and_verify_message(int expResponseCode, String expMessage) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        Invalid_url_Root_Output_Pojo invalidUrlRootOutputPojo = getbody().as(Invalid_url_Root_Output_Pojo.class);
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        assertEquals(expMessage, invalidUrlRootOutputPojo.message, "verify Msg");
    }

// <-------------------------------------------- Test 21 ------------------------------------------------------------->

    @When("user add request for IsExisting Flag along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_is_existing_flag_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {

        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
        addReqType("POST", getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for IsExisting having the response code as {int} and verify IsExisting Flag is {string}")
    public void validate_the_otp_generated_for_is_existing_having_the_response_code_as_and_verify_is_existing_flag_is(int expResponseCode, String expIsExisting) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        System.out.println(getbody().asPrettyString());
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2OutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        assertEquals(expIsExisting, verifyOtpTc2OutputPojo.data.isExisting, "verify isExisting");
    }
// <-------------------------------------------- Test 22 ------------------------------------------------------------->

    @When("user add requestBody for valid Login OTP {string} ,{string} and {string}")
    public void user_add_request_body_for_valid_login_OTP_and(String loginId, String loginType, String notification) throws IOException {
        addReqBody(payloadManager.getExternalLoginPayLoad().addLoginDetails(loginId, loginType, notification));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("Login"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",loginType=" + loginType + ",notification=" + notification);
        addReqType("POST", getPropertyFileValue("Login"));

    }

    @When("user add request for IsExisting Flag is zero along with loginId {string} , isdCode {string} ,deviceType {string} ,osVersion {string} ,appVersion {string} and deviceModel {string}")
    public void user_add_request_for_is_existing_flag_is_zero_along_with_login_id_isd_code_device_type_os_version_app_version_and_device_model(String loginId, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel) throws IOException {
        addReqBody(payloadManager.getVerifyOtpPayload().addDetails(loginId, getPropertyFileValue("Otp"), isdCode, deviceType, osVersion, appVersion, deviceModel, getPropertyFileValue("firebaseToken")));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Login URL is: " + getPropertyFileValue("LoginOtp"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: loginId=" + loginId + ",deviceType=" + deviceType + ",osVersion=" + osVersion + ",appVersion=" + appVersion + ",deviceModel" + deviceModel + ", firebaseToken" + getPropertyFileValue("firebaseToken"));
    }

    @When("the user sends a request for IsExisting Flag in Otp with {string}")
    public void the_user_sends_a_request_for_is_existing_flag_in_otp_with(String post) throws IOException {
        addReqType(post, getPropertyFileValue("LoginOtp"));
    }

    @Then("Validate the OTP Generated for IsExisting flag is zero having the response code as {int} and verify IsExisting Flag is {string}")
    public void validate_the_otp_generated_for_is_existing_flag_is_zero_having_the_response_code_as_and_verify_is_existing_flag_is(int expResponseCode, String expIsExisting) {

        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
        assertEquals(expResponseCode, getResponseCode(), "verify ResponseCode");
        Verify_Otp_Tc2_Root_Output_Pojo verifyOtpTc2OutputPojo = getbody().as(Verify_Otp_Tc2_Root_Output_Pojo.class);
        System.out.println(getbody().asPrettyString());
        assertEquals(expIsExisting, verifyOtpTc2OutputPojo.data.isExisting, "verify isExisting");
    }

// <-------------------------------------------- Test 23 ------------------------------------------------------------->

}









