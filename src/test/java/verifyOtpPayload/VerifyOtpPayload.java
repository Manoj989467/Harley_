package verifyOtpPayload;


import pojo_External_Login_Otp.Verify_Otp_Tc2_Data_Input_Pojo;
import pojo_External_Login_Otp.Verify_Otp_Tc2_Root_Input_Pojo;

public class VerifyOtpPayload {

    public Verify_Otp_Tc2_Root_Input_Pojo addDetails(String loginId, String otp, String isdCode, String deviceType, String osVersion, String appVersion, String deviceModel, String fireBaseToken) {

        Verify_Otp_Tc2_Data_Input_Pojo verifyOtpTc2DataInputPojo = new Verify_Otp_Tc2_Data_Input_Pojo(loginId, otp, isdCode, deviceType, osVersion, appVersion, deviceModel, fireBaseToken);
        Verify_Otp_Tc2_Root_Input_Pojo verifyOtpTc2RootInputPojo = new Verify_Otp_Tc2_Root_Input_Pojo(verifyOtpTc2DataInputPojo);

        return verifyOtpTc2RootInputPojo;

    }
}
