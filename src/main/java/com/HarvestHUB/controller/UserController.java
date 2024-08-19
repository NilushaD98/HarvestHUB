package com.HarvestHUB.controller;

import com.HarvestHUB.dto.request.CheckOTPDTO;
import com.HarvestHUB.dto.request.PasswordChangeDTO;
import com.HarvestHUB.dto.request.UserSignUpDTO;
import com.HarvestHUB.dto.request.UserUpdateDTO;
import com.HarvestHUB.dto.response.OtpResponse;
import com.HarvestHUB.service.UserService;
import com.HarvestHUB.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/harvesthub/api/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("sign_up")
    public ResponseEntity<StandardResponse> signUp(@RequestBody UserSignUpDTO userSignUpDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"User Sign up Status : ",userService.signUp(userSignUpDTO)), HttpStatus.OK
        );
    }
//    @GetMapping("send_otp_for_two_step_verification")
//    public ResponseEntity<StandardResponse> sendOTPtoEmail(@RequestParam("email")String email){
//        OtpResponse otpResponse = userService.sendOtpToEmail(email);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(200,"Otp Mail Send Status : ",otpResponse),HttpStatus.OK
//        );
//    }
//    @PostMapping("check_otp_for_two_step_verification")
//    public ResponseEntity<StandardResponse> checkOTP(@RequestBody CheckOTPDTO checkOTPDTO){
//        boolean otpCheckStatus = userService.checkOTP(checkOTPDTO);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(200,"OTP Checked Status : ", otpCheckStatus),HttpStatus.OK
//        );
//    }

    @GetMapping("get_user_by_id")
    public ResponseEntity<StandardResponse> getUserByID(@RequestParam("userID")String userID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Profile : ",userService.getUserByID(userID)),HttpStatus.OK
        );
    }
    @PutMapping("update_user")
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Update Status : ",userService.updateUser(userUpdateDTO)),HttpStatus.OK
        );
    }
    @PutMapping("password_change")
    public ResponseEntity<StandardResponse> passwordChange(@RequestBody PasswordChangeDTO passwordChangeDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Password Change Status : ",userService.passwordChange(passwordChangeDTO)),HttpStatus.OK
        );
    }
}
