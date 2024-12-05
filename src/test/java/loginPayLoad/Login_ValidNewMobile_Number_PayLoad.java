package loginPayLoad;


import pojoClass_ExternalLogin.Data_Login_NewMobileNumber_Input_Pojo;
import pojoClass_ExternalLogin.Root_Login_NewMobileNumber_Input_Pojo;

public class Login_ValidNewMobile_Number_PayLoad {

    public Root_Login_NewMobileNumber_Input_Pojo addLoginDetails(String loginId, String loginType, String notification) {


        Data_Login_NewMobileNumber_Input_Pojo dataLoginNewMobileNumberInputPojo = new Data_Login_NewMobileNumber_Input_Pojo(loginId,loginType,notification);
        Root_Login_NewMobileNumber_Input_Pojo rootLoginNewMobileNumberInputPojo = new Root_Login_NewMobileNumber_Input_Pojo(dataLoginNewMobileNumberInputPojo);

        return rootLoginNewMobileNumberInputPojo;
    }

}
