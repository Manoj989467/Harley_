package pojoClass_ExternalLogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Root_ExternalLogin_MoreThan_10DigitMobNumber_Output_Pojo {

	public boolean success;
	public String message;
	public int errorCode;
	public String errorDescription;

}
