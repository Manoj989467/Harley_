package pojo_UpdatePrimayVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Valid_Vin_Id_Root_Output {

	public boolean success;
	public String message;
	public Valid_Vin_Id_Data_Output data;
}
