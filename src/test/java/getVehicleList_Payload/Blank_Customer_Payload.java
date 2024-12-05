package getVehicleList_Payload;

import pojo_Class_GetVehicle_List.Blank_Customer_Identifier_Data_Input;
import pojo_Class_GetVehicle_List.Blank_Customer_Identifier_Root_Input;

public class Blank_Customer_Payload {

	public Blank_Customer_Identifier_Root_Input addDetails() {
		
		Blank_Customer_Identifier_Data_Input blank_Customer_Identifier_Data_Input = new Blank_Customer_Identifier_Data_Input();
		Blank_Customer_Identifier_Root_Input blank_Customer_Identifier_Root_Input = new Blank_Customer_Identifier_Root_Input(blank_Customer_Identifier_Data_Input);
		return blank_Customer_Identifier_Root_Input;
		
	}
	
	
	

}
