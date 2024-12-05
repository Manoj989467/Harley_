package getVehicleList_Payload;

import pojo_Class_GetVehicle_List.Invalid_Customer_identifier_Data_Input;
import pojo_Class_GetVehicle_List.Invalid_Customer_identifier_Root_Input;

public class Invalid_Customer_Identifier_Payload {

	public Invalid_Customer_identifier_Root_Input addDetails(String customerIdentifier) {

		Invalid_Customer_identifier_Data_Input customer_identifier_Data_Input = new Invalid_Customer_identifier_Data_Input(
				customerIdentifier);
		Invalid_Customer_identifier_Root_Input customer_identifier_Root_Input = new Invalid_Customer_identifier_Root_Input(
				customer_identifier_Data_Input);
		return customer_identifier_Root_Input;

	}

}
