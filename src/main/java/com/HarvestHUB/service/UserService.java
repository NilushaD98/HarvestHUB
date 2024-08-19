package com.HarvestHUB.service;

import com.HarvestHUB.dto.request.CheckOTPDTO;
import com.HarvestHUB.dto.request.PasswordChangeDTO;
import com.HarvestHUB.dto.request.UserSignUpDTO;
import com.HarvestHUB.dto.request.UserUpdateDTO;
import com.HarvestHUB.dto.response.AuthenticationSuccessDTO;
import com.HarvestHUB.dto.response.OtpResponse;
import com.HarvestHUB.dto.response.UserProfileDTO;

public interface UserService {
    AuthenticationSuccessDTO signUp(UserSignUpDTO userSignUpDTO);
    OtpResponse sendOtpToEmail(String email);

    boolean checkOTP(CheckOTPDTO checkOTPDTO);

    UserProfileDTO getUserByID(String userID);

    Boolean updateUser(UserUpdateDTO userUpdateDTO);

    Boolean passwordChange(PasswordChangeDTO passwordChangeDTO);
}
