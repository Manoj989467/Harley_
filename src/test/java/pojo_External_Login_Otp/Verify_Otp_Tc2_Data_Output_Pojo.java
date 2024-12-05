package pojo_External_Login_Otp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Verify_Otp_Tc2_Data_Output_Pojo {

    public String mediaToken;
    public String loginId;
    public String token;
    public String customerIdentifier;
    public String isdCode;
    public String accessToken;
    public String profilePicPath;
    public String isExisting;
    public String pnrConId;
    public String uniqueId;
    public String firstName;
    public String lastName;
    public String appName;
    public String mobileNo;
    public String primaryUser;
    public String accessKey;
}
