package pojo_Vin_Nickname;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Nickname_Valid_VinID_Root_Ouput {
	  public boolean success;
	    public String message;
	    public Nickname_Valid_VinID_Data_Ouput data;

}
