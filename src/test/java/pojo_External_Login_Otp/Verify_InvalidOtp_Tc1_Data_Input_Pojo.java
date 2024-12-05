package pojo_External_Login_Otp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Verify_InvalidOtp_Tc1_Data_Input_Pojo {


	public String loginId;
    public String otp;
    public String isdCode;
    public String deviceType;
    public String osVersion;
    public String appVersion;
    public String deviceModel;
    public String firebaseToken;

}
