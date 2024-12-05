package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import payloadManager.PayloadManager;
import pojo_Class_GetVehicle_List.Invalid_Customer_identifier_Root_Ouput;
import pojo_UpdatePrimayVehicle.Invalid_Url_Root_Output;
import pojo_UpdatePrimayVehicle.Invalid_Vin_Id_Root_Output;
import pojo_UpdatePrimayVehicle.Valid_Vin_Id_Root_Output;

public class Tc4_Update_Primary_Vehicle extends BaseClass {

	PayloadManager payloadManager = new PayloadManager();

	@Given("the login API is available and add headers and access token for update primary vehicle")
	public void the_login_api_is_available_and_add_headers_and_access_token_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "Bearer " + getPropertyFileValue("ValidVinIdtoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with valid customer Identifier and Vin Id for update primary vehicle")
	public void user_add_request_body_with_valid_customer_identifier_and_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {
		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("validVinIdIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier"));

	}

	@When("the user sends update primary vehicle request with {string}")
	public void the_user_sends_update_primary_vehicle_request_with(String post)
			throws FileNotFoundException, IOException {

		addReqType(post, getPropertyFileValue("UpdatePrimaryvehicle"));
	}

	@Then("the response status for update primary vehicle should be {int} and message {string} and isPrimaryVehicle is {string}")
	public void the_response_status_for_update_primary_vehicle_should_be_and_message_and_is_primary_vehicle_is(
			int expResponseCode, String expMessage, String expIsPrimaryVehicle) {
		
		System.out.print(getbody().asPrettyString());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Valid_Vin_Id_Root_Output root_Output = getbody().as(Valid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(expIsPrimaryVehicle, root_Output.data.isPrimaryVehicle, "verify primary value");
	}

//<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	

	@When("user add requestBody with valid customer Identifier and Invalid Vin Id for update primary vehicle")
	public void user_add_request_body_with_valid_customer_identifier_and_invalid_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails(getPropertyFileValue("InvalidVinId"),
				getPropertyFileValue("validVinIdIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("InvalidVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier"));

	}

	@Then("the response status for update primary vehicle for Invalid Vin Id should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_primary_vehicle_for_invalid_vin_id_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

	}

//<---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	

	@When("user add requestBody with valid customer Identifier and BlankVinId for update primary vehicle")
	public void user_add_request_body_with_valid_customer_identifier_and_blank_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails("",
				getPropertyFileValue("validVinIdIdentifier")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: VinId=" + "" + ",Customer Identifier="
				+ getPropertyFileValue("validVinIdIdentifier"));

	}

	@Then("the response status for update primary vehicle for blank vinId should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_primary_vehicle_for_blank_vin_id_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify  Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

	}

//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with Invalid customer Identifier and Valid VinId for update primary vehicle")
	public void user_add_request_body_with_invalid_customer_identifier_and_valid_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("InvalidcustomerIdentifier")));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("InvalidcustomerIdentifier"));

	}

	@Then("the response status for update primary vehicle for invalid customer identifier should be {int} and message {string} , errorCode {int}")
	public void the_response_status_for_update_primary_vehicle_for_invalid_customer_identifier_should_be_and_message_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Customer_identifier_Root_Ouput root_Ouput = getbody().as(Invalid_Customer_identifier_Root_Ouput.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expErrorCode, root_Ouput.errorCode, "verify is Error code");
	}

	// <-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with BlankCustomerIdentifier and valid VinId for update primary vehicle")
	public void user_add_request_body_with_blank_customer_identifier_and_valid_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails(getPropertyFileValue("validVinId"), ""));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier=" + " ");

	}

	@Then("the response status for update primary vehicle for BlankCustomerIdentifier should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_primary_vehicle_for_blank_customer_identifier_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with SpecialCharCustomerIdentifier and valid VinId for update primary vehicle")
	public void user_add_request_body_with_special_char_customer_identifier_and_valid_vin_id_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getUpdate_Primary_Vehicle().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("specialcharcustomerIdentifier")));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update primary vehicle URL is: " + getPropertyFileValue("UpdatePrimaryvehicle"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("specialcharcustomerIdentifier"));

	}

	@Then("the response status for update primary vehicle for SpecialCharCustomerIdentifier should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_primary_vehicle_for_special_char_customer_identifier_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
	@Given("the login API is available and add headers and invalid access token for update primary vehicle")
	public void the_login_api_is_available_and_add_headers_and_invalid_access_token_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "Bearer " + getPropertyFileValue("InvalidToken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@Then("the response status for update primary vehicle for invalid token should be {int} and message {string} , errorCode {int}")
	public void the_response_status_for_update_primary_vehicle_for_invalid_token_should_be_and_message_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Customer_identifier_Root_Ouput root_Ouput = getbody().as(Invalid_Customer_identifier_Root_Ouput.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expErrorCode, root_Ouput.errorCode, "verify is Error code");

	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers and Blank access token for update primary vehicle")
	public void the_login_api_is_available_and_add_headers_and_blank_access_token_for_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "");
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}

	@Then("the response status for update primary vehicle for Blank token should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_primary_vehicle_for_blank_token_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		System.out.println(getbody().asPrettyString());
		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("the user sends update primary vehicle  for invalid url request with {string} and url {string}")
	public void the_user_sends_update_primary_vehicle_for_invalid_url_request_with_and_url(String post,
			String invalidUrl) {

		addReqType(post, invalidUrl);
	}

	@Then("the response status for update primary vehicle for Invalid Vin Id should be {int} and message {string}")
	public void the_response_status_for_update_primary_vehicle_for_invalid_vin_id_should_be_and_message(
			int expResponseCode, String expMessage) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Url_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Url_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(expResponseCode, invalid_Vin_Id_Root_Output.statusCode, "verify status code");
	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@Then("the response status for update primary vehicle for empty request body should be {int}")
	public void the_response_status_for_update_primary_vehicle_for_empty_request_body_should_be(int expResponseCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());

		assertEquals(expResponseCode, getResponseCode(), "verify response code");

	}
	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid specific appid in update primary vehicle")
	public void the_login_api_is_available_and_add_headers_for_invalid_specific_appid_in_update_primary_vehicle()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", "HarleyX440");
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "Bearer " + getPropertyFileValue("ValidVinIdtoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	
		@Then("the response status for Invalid appid in update primary vehicle list should be {int} and message {string} , errorcode {int} , errorDescription {string}")
		public void the_response_status_for_invalid_appid_in_update_primary_vehicle_list_should_be_and_message_errorcode_error_description(int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");

		}
	

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid appid in update primary vehicle")
	public void the_login_api_is_available_and_add_headers_for_invalid_appid_in_update_primary_vehicle()
			throws FileNotFoundException, IOException {
		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "ExternalLogin");
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "Bearer " + getPropertyFileValue("ValidVinIdtoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);

	}
	
	@Then("the response status for Invalid specific appid in update primary vehicle list  should be {int} and message {string} , errorcode {int} , errorDescription {string}")
	public void the_response_status_for_invalid_specific_appid_in_update_primary_vehicle_list_should_be_and_message_errorcode_error_description(int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Vin_Id_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(experrorCode, invalid_Vin_Id_Root_Output.errorCode, "verify Error code");
		assertEquals(expErrorDescription, invalid_Vin_Id_Root_Output.errorDescription, "verify Error Decsription");
   
	}

	// <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@Given("the login API is available and add headers for Invalid_ocp-apim-subscription-key in update primary vehicle")
	public void the_login_api_is_available_and_add_headers_for_invalid_ocp_apim_subscription_key_in_update_primary_vehicle() throws FileNotFoundException, IOException {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", "ExternalLogin");
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key","31110036a520491dacc8a96ee18aaa618686");
		Header h4 = new Header("Authorization", "Bearer " + getPropertyFileValue("ValidVinIdtoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@Then("the response status for Invalid_ocp-apim-subscription-key in update primary vehicle should be {int} and message {string}")
	public void the_response_status_for_invalid_ocp_apim_subscription_key_in_update_primary_vehicle_should_be_and_message(
			int expResponseCode, String expMessage) {
	
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		System.out.println(getbody().asPrettyString());
		Invalid_Url_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Url_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
	}

}
