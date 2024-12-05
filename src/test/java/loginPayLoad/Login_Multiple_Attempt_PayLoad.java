package loginPayLoad;


import pojoClass_ExternalLogin.Data_ExternalLogin_Input_Pojo;
import pojoClass_ExternalLogin.Root_ExternalLogin_Input_Pojo;

public class Login_Multiple_Attempt_PayLoad {

    public Root_ExternalLogin_Input_Pojo addLoginDetails(String loginId, String loginType, String notificationType) {

        Data_ExternalLogin_Input_Pojo dataExternalLoginInput = new Data_ExternalLogin_Input_Pojo(loginId, loginType, notificationType);
        Root_ExternalLogin_Input_Pojo rootLoginInput = new Root_ExternalLogin_Input_Pojo(dataExternalLoginInput);

        return rootLoginInput;
    }


}
