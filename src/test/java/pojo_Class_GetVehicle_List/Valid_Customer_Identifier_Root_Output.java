package pojo_Class_GetVehicle_List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Valid_Customer_Identifier_Root_Output {

	public boolean success;
	public String message;
	public Valid_Customer_Identifier_Data_Output data;

}
