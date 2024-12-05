package loginPayLoad;

import pojoClass_ExternalLogin.Data_InvalidLogin_Input;
import pojoClass_ExternalLogin.Root_InvalidLogin_Input;

public class LoginType_MaximumChar_Payload {

    public Root_InvalidLogin_Input addDetails(String loginId, String loginType, String notification) {

        Data_InvalidLogin_Input data_login_input = new Data_InvalidLogin_Input(loginId, loginType, notification);
        Root_InvalidLogin_Input rootLoginInput = new Root_InvalidLogin_Input(data_login_input);

        return rootLoginInput;
    }
}