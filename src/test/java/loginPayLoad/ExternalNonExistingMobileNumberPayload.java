package loginPayLoad;


import pojoClass_ExternalLogin.Data_ExternalLogin_NonExisting_MobNumber_Input_Pojo;
import pojoClass_ExternalLogin.Root_ExternalLogin_NonExisting_MobNumber_Input_Pojo;

public class ExternalNonExistingMobileNumberPayload {

    public Root_ExternalLogin_NonExisting_MobNumber_Input_Pojo addLoginDetails(String loginId, String loginType, String notificationType) {
        Data_ExternalLogin_NonExisting_MobNumber_Input_Pojo dataExternalLoginNonExistingMobNumberInputPojo = new Data_ExternalLogin_NonExisting_MobNumber_Input_Pojo(loginId,loginType,notificationType);
        Root_ExternalLogin_NonExisting_MobNumber_Input_Pojo rootExternalLoginNonExistingMobNumberInputPojo = new Root_ExternalLogin_NonExisting_MobNumber_Input_Pojo(dataExternalLoginNonExistingMobNumberInputPojo);
        return rootExternalLoginNonExistingMobNumberInputPojo;
    }
}
