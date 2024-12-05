package loginPayLoad;


import pojoClass_ExternalLogin.Data_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo;
import pojoClass_ExternalLogin.Root_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo;

public class Login_MoreThan_10DigitMobNumber_Payload {

    public Root_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo adddetails(String loginId, String LoginType, String notification){

        Data_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo dataExternalLoginMoreThan10DigitMobNumberInputPojo = new Data_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo(loginId,LoginType,notification);
        Root_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo rootExternalLoginMoreThan10DigitMobNumberInputPojo = new Root_ExternalLogin_MoreThan_10DigitMobNumber_Input_Pojo(dataExternalLoginMoreThan10DigitMobNumberInputPojo);
          return rootExternalLoginMoreThan10DigitMobNumberInputPojo;

    }


}
