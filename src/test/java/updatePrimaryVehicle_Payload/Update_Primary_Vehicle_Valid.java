package updatePrimaryVehicle_Payload;

import pojo_UpdatePrimayVehicle.Valid_Vin_Id_Data_Input;
import pojo_UpdatePrimayVehicle.Valid_Vin_Id_Root_Input;

public class Update_Primary_Vehicle_Valid {

	public Valid_Vin_Id_Root_Input addDetails(String vinId,String customerIdentifier) {
		
		
		Valid_Vin_Id_Data_Input  data_Input = new Valid_Vin_Id_Data_Input(vinId,customerIdentifier);
		Valid_Vin_Id_Root_Input Root_Input = new Valid_Vin_Id_Root_Input(data_Input);
		return Root_Input;
		
	}
	
	
	

}
