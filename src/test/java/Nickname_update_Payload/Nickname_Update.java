package Nickname_update_Payload;

import pojo_Vin_Nickname.Nickname_Valid_VinID_Data_Input;
import pojo_Vin_Nickname.Nickname_Valid_VinID_Root_Input;

public class Nickname_Update {
	
	
	public Nickname_Valid_VinID_Root_Input addDetails(String vinId,String customerIdentifier, String vehicleNickName) {
		
		Nickname_Valid_VinID_Data_Input data_Input =new Nickname_Valid_VinID_Data_Input(vinId,customerIdentifier,vehicleNickName);
		Nickname_Valid_VinID_Root_Input Root_Input = new Nickname_Valid_VinID_Root_Input(data_Input);
		return Root_Input;
	}

}
