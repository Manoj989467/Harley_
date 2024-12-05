package loginPayLoad;

import pojoClass_ExternalLogin.Data_SpecialChar_Invalid_MobileNumber;
import pojoClass_ExternalLogin.Root_SpecialChar_Invalid_MobileType_Input;

public class SpecialChar_LoginId_PayLoad {

    public Root_SpecialChar_Invalid_MobileType_Input addSpecialCharLoginIdPayLoad(String loginId, String loginType,String notification) {

        Data_SpecialChar_Invalid_MobileNumber dataSpecialCharInvalidMobileNumber = new Data_SpecialChar_Invalid_MobileNumber(loginId, loginType, notification);
        Root_SpecialChar_Invalid_MobileType_Input rootSpecialCharInvalidMobileTypeInput = new Root_SpecialChar_Invalid_MobileType_Input(dataSpecialCharInvalidMobileNumber);
        return rootSpecialCharInvalidMobileTypeInput;
    }


}
