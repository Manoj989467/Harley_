package pojo_External_Login_Otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Verify_Otp_Tc2_Root_Output_Pojo {

    public boolean success;
    public String message;
    public Verify_Otp_Tc2_Data_Output_Pojo data;

}
