package pojoClass_ExternalLogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Data_InvalidLogin_Input {

   
	public String loginId;
    public String loginType;
    public String notificationType;
	
}
