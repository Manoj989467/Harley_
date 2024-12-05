package payloadManager;

import Nickname_update_Payload.Nickname_Update;
import getVehicleList_Payload.Blank_Customer_Payload;
import getVehicleList_Payload.Invalid_Customer_Identifier_Payload;
import getVehicleList_Payload.Valid_Customer_Identifier;
import loginPayLoad.*;
import updatePrimaryVehicle_Payload.Update_Primary_Vehicle_Valid;
import verifyOtpPayload.OtpInvalidPayload;
import verifyOtpPayload.VerifyOtpPayload;

public class PayloadManager {

    public InValid_LoginInput_PayLoad addInValid_LoginInput_PayLoad;
    public External_Login_PayLoad externalLoginPayLoad;
    public ExternalNonExistingMobileNumberPayload externalNonExistingMobileNumberPayload;
    public SpecialChar_LoginId_PayLoad specialCharLoginIdPayLoad;
    public Login_MoreThan_10DigitMobNumber_Payload loginMoreThan10DigitMobNumberPayload;
    public Login_Blank_InputType_Payload loginBlankInputTypePayload;
    public LoginType_MaximumChar_Payload loginTypeMaximumCharPayload;
    public Login_ValidNewMobile_Number_PayLoad loginValidNewMobileNumberPayLoad;
    public Login_Invalid_AppId_PayLoad loginInvalidAppIdPayLoad;
    public Login_Blank_AppId_PayLoad loginBlankAppIdPayLoad;
    public Login_Invalid_SpecialAppId_PayLoad loginInvalidSpecialAppIdPayLoad;
    public Login_Blank_SpecialAppId_PayLoad loginBlankSpecialAppIdPayLoad;
    public Login_Multiple_Attempt_PayLoad loginMultipleAttemptPayLoad;
    public VerifyOtpPayload verifyOtpPayload;
    public OtpInvalidPayload otpInvalidPayload;
    public Invalid_Customer_Identifier_Payload inValidcustomerIdentifierPayload;
    public Valid_Customer_Identifier validcustomeridentifier;
    public Blank_Customer_Payload blank_Customer_Payload;
    public Update_Primary_Vehicle_Valid update_Primary_Vehicle;
    public Nickname_Update nickname_Update;
    
    public InValid_LoginInput_PayLoad addInValid_LoginInput_PayLoad() {

        return (addInValid_LoginInput_PayLoad == null) ? addInValid_LoginInput_PayLoad = new InValid_LoginInput_PayLoad() : addInValid_LoginInput_PayLoad;
    }

    public External_Login_PayLoad getExternalLoginPayLoad() {
        return (externalLoginPayLoad == null) ? externalLoginPayLoad = new External_Login_PayLoad() : externalLoginPayLoad;
    }


    public ExternalNonExistingMobileNumberPayload addGetExternalNonExistingMobileNumberPayload() {
        return (externalNonExistingMobileNumberPayload == null) ? externalNonExistingMobileNumberPayload = new ExternalNonExistingMobileNumberPayload() : externalNonExistingMobileNumberPayload;
    }

    public SpecialChar_LoginId_PayLoad addSpecialCharLoginIdPayLoad() {
        return (specialCharLoginIdPayLoad == null) ? specialCharLoginIdPayLoad = new SpecialChar_LoginId_PayLoad() : specialCharLoginIdPayLoad;
    }

    public Login_MoreThan_10DigitMobNumber_Payload getLoginMoreThan10DigitMobNumberPayload() {
        return (loginMoreThan10DigitMobNumberPayload == null) ? loginMoreThan10DigitMobNumberPayload = new Login_MoreThan_10DigitMobNumber_Payload() : loginMoreThan10DigitMobNumberPayload;
    }

    public Login_Blank_InputType_Payload getLoginBlankInputTypePayload() {
        return (loginBlankInputTypePayload == null) ? loginBlankInputTypePayload = new Login_Blank_InputType_Payload() : loginBlankInputTypePayload;

    }

    public LoginType_MaximumChar_Payload getLoginTypeMaximumCharPayload() {
        return (loginTypeMaximumCharPayload == null) ? loginTypeMaximumCharPayload = new LoginType_MaximumChar_Payload() : loginTypeMaximumCharPayload;

    }

    public Login_ValidNewMobile_Number_PayLoad getLoginValidNewMobileNumberPayLoad() {
        return (loginValidNewMobileNumberPayLoad == null) ? loginValidNewMobileNumberPayLoad = new Login_ValidNewMobile_Number_PayLoad() : loginValidNewMobileNumberPayLoad;

    }

    public Login_Invalid_AppId_PayLoad getLoginInvalidAppIdPayLoad() {
        return (loginInvalidAppIdPayLoad == null) ? loginInvalidAppIdPayLoad = new Login_Invalid_AppId_PayLoad() : loginInvalidAppIdPayLoad;

    }

    public Login_Blank_AppId_PayLoad getLoginBlankAppIdPayLoad() {
        return (loginBlankAppIdPayLoad == null) ? loginBlankAppIdPayLoad = new Login_Blank_AppId_PayLoad() : loginBlankAppIdPayLoad;

    }

    public Login_Invalid_SpecialAppId_PayLoad getLoginInvalidSpecialAppIdPayLoad() {
        return (loginInvalidSpecialAppIdPayLoad == null) ? loginInvalidSpecialAppIdPayLoad = new Login_Invalid_SpecialAppId_PayLoad() : loginInvalidSpecialAppIdPayLoad;

    }

    public Login_Blank_SpecialAppId_PayLoad getLoginBlankSpecialAppIdPayLoad() {
        return (loginBlankSpecialAppIdPayLoad == null) ? loginBlankSpecialAppIdPayLoad = new Login_Blank_SpecialAppId_PayLoad() : loginBlankSpecialAppIdPayLoad;

    }

    public Login_Multiple_Attempt_PayLoad getLoginMultipleAttemptPayLoad() {
        return (loginMultipleAttemptPayLoad == null) ? loginMultipleAttemptPayLoad = new Login_Multiple_Attempt_PayLoad() : loginMultipleAttemptPayLoad;

    }

    public VerifyOtpPayload getVerifyOtpPayload() {
        return (verifyOtpPayload == null) ? verifyOtpPayload = new VerifyOtpPayload() : verifyOtpPayload;

    }

    public OtpInvalidPayload getOtpInvalidPayload(){
        return (otpInvalidPayload == null) ? otpInvalidPayload = new OtpInvalidPayload() : otpInvalidPayload;

    }

    public Invalid_Customer_Identifier_Payload getInvalid_Customer_Identifier_Payload(){
        return (inValidcustomerIdentifierPayload == null) ? inValidcustomerIdentifierPayload = new Invalid_Customer_Identifier_Payload() : inValidcustomerIdentifierPayload;

    }
    
    public Valid_Customer_Identifier getValid_Customer_Identifier(){
        return (validcustomeridentifier == null) ? validcustomeridentifier = new Valid_Customer_Identifier() : validcustomeridentifier;

    }
    
    public Blank_Customer_Payload getBlank_Customer_Payload(){
        return (blank_Customer_Payload == null) ? blank_Customer_Payload = new Blank_Customer_Payload() : blank_Customer_Payload;

    }
    
    public Update_Primary_Vehicle_Valid getUpdate_Primary_Vehicle(){
        return (update_Primary_Vehicle == null) ? update_Primary_Vehicle = new Update_Primary_Vehicle_Valid() : update_Primary_Vehicle;

    }
    
    public Nickname_Update getNickname_Update(){
        return (nickname_Update == null) ? nickname_Update = new Nickname_Update() : nickname_Update;

    }
}




