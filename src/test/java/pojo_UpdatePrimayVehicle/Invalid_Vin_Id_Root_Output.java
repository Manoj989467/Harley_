package pojo_UpdatePrimayVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invalid_Vin_Id_Root_Output {
	
    public boolean success;
    public String message;
    public int errorCode;
    public String errorDescription;

}
