package verifyOtpPayload;


import pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Data_Input_Pojo;
import pojo_External_Login_Otp.Verify_InvalidOtp_Tc1_Root_Input_Pojo;

public class OtpInvalidPayload {

    public Verify_InvalidOtp_Tc1_Root_Input_Pojo addDetails(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel, String fireBaseToken) {

        Verify_InvalidOtp_Tc1_Data_Input_Pojo verifyOtpTc1DataInputPojo = new Verify_InvalidOtp_Tc1_Data_Input_Pojo(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, fireBaseToken);
        Verify_InvalidOtp_Tc1_Root_Input_Pojo verifyInvalidOtpTc1RootInputPojo = new Verify_InvalidOtp_Tc1_Root_Input_Pojo(verifyOtpTc1DataInputPojo);
        return verifyInvalidOtpTc1RootInputPojo;
    }
}
