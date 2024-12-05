package pojo_Class_GetVehicle_List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invalid_Appid_Root_Ouput {

	public boolean success;
	public String message;
	public int errorCode;
	public String errorDescription;

}
