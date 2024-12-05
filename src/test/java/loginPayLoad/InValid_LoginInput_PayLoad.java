package loginPayLoad;


import pojoClass_ExternalLogin.Data_InvalidLogin_Input;
import pojoClass_ExternalLogin.Root_InvalidLogin_Input;


public class InValid_LoginInput_PayLoad {

    public Root_InvalidLogin_Input addLoginDetails(String loginid, String logintype, String notificationtype) {

        Data_InvalidLogin_Input data_login_input = new Data_InvalidLogin_Input(loginid, logintype, notificationtype);
        Root_InvalidLogin_Input rootLoginInput = new Root_InvalidLogin_Input(data_login_input);

        return rootLoginInput;

    }
}
