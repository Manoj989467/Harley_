package pojo_UpdatePrimayVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invalid_Customer_Identifier {
	public boolean success;
	public String message;
	public int errorCode;

}
