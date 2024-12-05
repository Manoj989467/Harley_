package pojo_External_Login_Otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Verify_InvalidOtp_Tc1_Root_Output_Pojo {

    public boolean success;
    public String message;
    public int errorCode;
    public String errorDescription;
}
