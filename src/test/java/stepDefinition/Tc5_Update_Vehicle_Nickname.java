package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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
import pojo_Vin_Nickname.Invalid_Vin_Id_Nickname_Root_Output;
import pojo_Vin_Nickname.Nickname_Valid_VinID_Root_Ouput;

public class Tc5_Update_Vehicle_Nickname extends BaseClass {

	PayloadManager payloadManager = new PayloadManager();

	@Given("the login API is available and add headers and access token for update vehicle nickname")
	public void the_login_api_is_available_and_add_headers_and_access_token_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", "Bearer " 
		                      + getPropertyFileValue("ValidVinIdtoken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@When("user add requestBody with valid customer Identifier and Vin Id for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_and_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {
		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("validVinIdIdentifier"), getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname="
						+ getPropertyFileValue("Nickname"));
	}

	@When("the user sends update vehicle nickname request with {string}")
	public void the_user_sends_update_vehicle_nickname_request_with(String post)
			throws FileNotFoundException, IOException {

		addReqType(post, getPropertyFileValue("UpdateVehicleNickname"));
	}

	@Then("the response status for update vehicle nickname should be {int} and message {string} , vehicle nickname {string} and updated message {string}")
	public void the_response_status_for_update_vehicle_nickname_should_be_and_message_vehicle_nickname_and_updated_message(
			int expResponseCode, String expMessage, String expNickName, String expUpdatedMsg) {
System.out.println(getbody().asPrettyString());
		
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Nickname_Valid_VinID_Root_Ouput root_Ouput = getbody().as(Nickname_Valid_VinID_Root_Ouput.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expNickName, root_Ouput.data.vehicleNickName, "verify vehicle nickname");
		assertEquals(expUpdatedMsg, root_Ouput.data.message, "verify vehicle nickname updated message");
	}
//<------------------------------------------------------------------------------------------------------------------------------------------------------------------->	

	@When("user add requestBody with valid customer Identifier and Invalid Vin Id for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_and_invalid_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("InvalidVinId"),
				getPropertyFileValue("validVinIdIdentifier"), getPropertyFileValue("Nickname")));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("InvalidVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname="
						+ getPropertyFileValue("Nickname"));
	}

	@Then("the response status for update vehicle nickname for Invalid Vin Id should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nickname_for_invalid_vin_id_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");

	}

//<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>	

	@When("user add requestBody with valid customer Identifier ,Vin Id and invalid nick name for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_vin_id_and_invalid_nick_name_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("validVinIdIdentifier"), getPropertyFileValue("Invalidnickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname="
						+ getPropertyFileValue("Invalidnickname"));

	}

	@Then("the response status for update vehicle nickname for Invalid nick name should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nickname_for_invalid_nick_name_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");
	}

//<---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	
	@When("user add requestBody with valid customer Identifier, valid nick name and BlankVinId for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_valid_nick_name_and_blank_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails("", getPropertyFileValue("validVinIdIdentifier"),
				getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: VinId=" + "" + ",Customer Identifier="
				+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname=" + getPropertyFileValue("Nickname"));

	}

	@Then("the response status for update vehicle nickname for blank vinId should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nickname_for_blank_vin_id_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");

	}
//<---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with valid customer Identifier, valid nick name and specialcharVinId for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_valid_nick_name_and_specialchar_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("specialcharVinId"),
				getPropertyFileValue("validVinIdIdentifier"), getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request_payload: VinId=" + "" + ",Customer Identifier="
				+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname=" + getPropertyFileValue("Nickname"));

	}

	@Then("the response status for update vehicle nickname for specialcharVinId should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nickname_for_specialchar_vin_id_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		System.out.println(getbody().asPrettyString());
		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");
	}
//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with valid customer Identifier ,Vin Id and SpecialcharNickname for update vehicle nickname")
	public void user_add_request_body_with_valid_customer_identifier_vin_id_and_specialchar_nickname_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("validVinIdIdentifier"), getPropertyFileValue("specialCharNickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname="
						+ getPropertyFileValue("specialCharNickname"));

	}

	@Then("the response status for update vehicle nickname for SpecialcharNickname should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nickname_for_specialchar_nickname_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");
	}
//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

	@When("user add requestBody with valid customer Identifier ,Vin Id and Space in btw Nickname for update vehicle nickname {string}")
	public void user_add_request_body_with_valid_customer_identifier_vin_id_and_space_in_btw_nickname_for_update_vehicle_nickname(String nickName) throws FileNotFoundException, IOException {
		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("validVinIdIdentifier"), nickName));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("validVinIdIdentifier") + ",Nickname="
						+ nickName);

	}
	

	@Then("the response status for Space in btw Nickname in update vehicle nickname should be {int} and message {string} , vehicle nickname {string} and updated message {string}")
	public void the_response_status_for_space_in_btw_nickname_in_update_vehicle_nickname_should_be_and_message_vehicle_nickname_and_updated_message(
			int expResponseCode, String expMessage, String expNickName, String expUpdatedMsg) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Nickname_Valid_VinID_Root_Ouput root_Ouput = getbody().as(Nickname_Valid_VinID_Root_Ouput.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expNickName, root_Ouput.data.vehicleNickName, "verify vehicle nickname");
		assertEquals(expUpdatedMsg, root_Ouput.data.message, "verify vehicle nickname updated message");
	}
	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>

	@When("user add requestBody with Invalid customer Identifier ,Vin Id and valid nick name for update vehicle nickname")
	public void user_add_request_body_with_invalid_customer_identifier_vin_id_and_valid_nick_name_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("InvalidcustomerIdentifier"), getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("InvalidcustomerIdentifier") + ",Nickname="
						+ getPropertyFileValue("Nickname"));

	}

	@Then("the response status for update vehicle nick name for invalid customer identifier should be {int} and message {string} , errorCode {int}")
	public void the_response_status_for_update_vehicle_nick_name_for_invalid_customer_identifier_should_be_and_message_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Customer_identifier_Root_Ouput root_Ouput = getbody().as(Invalid_Customer_identifier_Root_Ouput.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expErrorCode, root_Ouput.errorCode, "verify error code");
		assertFalse(root_Ouput.success);
	}
	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>

	@When("user add requestBody with SpecialCharCustomerIdentifier and valid VinId for update vehicle nickname")
	public void user_add_request_body_with_special_char_customer_identifier_and_valid_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"),
				getPropertyFileValue("specialcharcustomerIdentifier"), getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier="
						+ getPropertyFileValue("specialcharcustomerIdentifier") + ",Nickname="
						+ getPropertyFileValue("Nickname"));

	}

	@Then("the response status for update vehicle nick name for SpecialCharCustomerIdentifier should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nick_name_for_special_char_customer_identifier_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");

	}
	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>

	@When("user add requestBody with BlankIdentifier and valid VinId for update vehicle nickname")
	public void user_add_request_body_with_blank_identifier_and_valid_vin_id_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		addReqBody(payloadManager.getNickname_Update().addDetails(getPropertyFileValue("validVinId"), "",
				getPropertyFileValue("Nickname")));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"update vehicle nickname URL is: " + getPropertyFileValue("UpdateVehicleNickname"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request_payload: VinId=" + getPropertyFileValue("validVinId") + ",Customer Identifier=" + ""
						+ ",Nickname=" + getPropertyFileValue("Nickname"));

	}

	@Then("the response status for update vehicle nick name for BlankIdentifier should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nick_name_for_blank_identifier_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		System.out.println(getbody().asPrettyString());

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");
	}

	// <------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>
	@Given("the login API is available and add headers and invalid access token for update vehicle nickname")
	public void the_login_api_is_available_and_add_headers_and_invalid_access_token_for_update_vehicle_nickname()
			throws FileNotFoundException, IOException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		List<Header> list_headers = new ArrayList<>();
		Header h1 = new Header("appid", getPropertyFileValue("appid"));
		Header h2 = new Header("specificAppId", getPropertyFileValue("specificAppId"));
		Header h3 = new Header("ocp-apim-subscription-key", getPropertyFileValue("subscriptionkey"));
		Header h4 = new Header("Authorization", getPropertyFileValue("InvalidToken"));
		list_headers.add(h1);
		list_headers.add(h2);
		list_headers.add(h3);
		list_headers.add(h4);
		Headers headers = new Headers(list_headers);
		addHeaders(headers);
	}

	@Then("the response status for update vehicle nick name for invalid token should be {int} and message {string} , errorCode {int}")
	public void the_response_status_for_update_vehicle_nick_name_for_invalid_token_should_be_and_message_error_code(
			int expResponseCode, String expMessage, int expErrorCode) {

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Customer_identifier_Root_Ouput root_Ouput = getbody().as(Invalid_Customer_identifier_Root_Ouput.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Ouput.message, "verify message");
		assertEquals(expErrorCode, root_Ouput.errorCode, "verify error code");
		assertFalse(root_Ouput.success);

	}
//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	

	@Given("the login API is available and add headers and BlankToken for update vehicle nickname")
	public void the_login_api_is_available_and_add_headers_and_blank_token_for_update_vehicle_nickname()
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

	@Then("the response status for update vehicle nick name for Blank token should be {int} and message {string} , errorCode {int} and errorDescription {string}")
	public void the_response_status_for_update_vehicle_nick_name_for_blank_token_should_be_and_message_error_code_and_error_description(
			int expResponseCode, String expMessage, int experrorCode, String expErrorDescription) {

		System.out.println(getbody().asPrettyString());

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());
		Invalid_Vin_Id_Nickname_Root_Output root_Output = getbody().as(Invalid_Vin_Id_Nickname_Root_Output.class);
		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, root_Output.message, "verify message");
		assertEquals(experrorCode, root_Output.errorCode, "verify is Error code");
		assertEquals(expErrorDescription, root_Output.errorDescription, "verify Error Decsription");

	}

//<---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	
	@When("the user sends update vehicle nickname request with {string} and url {string}")
	public void the_user_sends_update_vehicle_nickname_request_with_and_url(String post, String inValidUrl) {
		addReqType(post, inValidUrl);

	}

	@Then("the response status for update vehicle nickname for Invalid url should be {int} and message {string}")
	public void the_response_status_for_update_vehicle_nickname_for_invalid_url_should_be_and_message(
			int expResponseCode, String expMessage) {

		System.out.println(getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Generated is: " + getbody().asPrettyString());

		Invalid_Url_Root_Output invalid_Vin_Id_Root_Output = getbody().as(Invalid_Url_Root_Output.class);

		assertEquals(expResponseCode, getResponseCode(), "verify response code");
		assertEquals(expMessage, invalid_Vin_Id_Root_Output.message, "verify message");
		assertEquals(expResponseCode, invalid_Vin_Id_Root_Output.statusCode, "verify status code");
	}
//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->	
	
	@Then("the response status for update vehicle nick name for empty request body should be {int}")
	public void the_response_status_for_update_vehicle_nick_name_for_empty_request_body_should_be(Integer int1) {

		System.out.println(getResponseCode());
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Status code is: " + getResponseCode());
	}

//<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
}
