package com.HarvestHUB.service.IMPL;

import com.HarvestHUB.collection.OTP;
import com.HarvestHUB.collection.User;
import com.HarvestHUB.dto.request.*;
import com.HarvestHUB.dto.response.AuthenticationSuccessDTO;
import com.HarvestHUB.dto.response.OtpResponse;
import com.HarvestHUB.dto.response.UserProfileDTO;
import com.HarvestHUB.exeption.EmailSenderErrorResponse;
import com.HarvestHUB.exeption.UserAlreadyReportedException;
import com.HarvestHUB.exeption.UserNotFoundException;
import com.HarvestHUB.repo.OTPRepository;
import com.HarvestHUB.repo.UserRepository;
import com.HarvestHUB.service.AuthService;
import com.HarvestHUB.service.HarvestService;
import com.HarvestHUB.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final JavaMailSender javaMailSender;
    private final OTPRepository otpRepository;
    private final HarvestService harvestService;
    @Override
    public AuthenticationSuccessDTO signUp(UserSignUpDTO userSignUpDTO) {
        log.info(userSignUpDTO.toString());
        Optional<User> userByEmail = userRepository.findUserByEmailEquals(userSignUpDTO.getEmail());
        if(userByEmail.isPresent()){
            throw new UserAlreadyReportedException();
        }else {
            User user = new User(
                    UUID.randomUUID().toString().substring(0,10),
                    userSignUpDTO.getEmail(),
                    userSignUpDTO.getName(),
                    userSignUpDTO.getAddress(),
                    userSignUpDTO.getContact(),
                    passwordEncoder.encode(userSignUpDTO.getPassword()),
                    userSignUpDTO.getRole().toString()
            );
            user.setProfilePicture(userSignUpDTO.getUserProfilePictureURL());
            userRepository.save(user);

            return authService.authenticate(
                    new AuthenticationDTO(user.getEmail(), userSignUpDTO.getPassword())
            );
        }
    }
    public OtpResponse sendOtpToEmail(String email) {
        int otp = generateRandomOTP();
        boolean vortexOtp = mailSender(email, "Harvest HUB OTP", " Your Harvest HUB verification code is - " + Integer.toString(otp));
        if(vortexOtp){
            OTP otp1 = new OTP(
                    email,
                    Integer.toString(otp),
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis()+ 1*60*1000),//1 min otp
                    false
            );
            otpRepository.save(otp1);
            return new OtpResponse(true);
        }else {
            throw new EmailSenderErrorResponse();
        }
    }
    public static int generateRandomOTP() {
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public boolean mailSender(String toMail, String subject, String body){
        try {
            SimpleMailMessage newMail = new SimpleMailMessage();
            newMail.setTo(toMail);
            newMail.setSubject(subject);
            newMail.setText(body);
            javaMailSender.send(newMail);
            log.info("Mail successfully send to "+ toMail);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
    @Scheduled(fixedRate = 1*10*1000)
    public void cleanupExpiredOTPRecords() {
        Date currentTime = new Date(System.currentTimeMillis());
        otpRepository.deleteOTPSByOtpExpirationTimeBefore(currentTime);
        log.info("Expired OTP Deleted");
    }
    @Override
    public boolean checkOTP(CheckOTPDTO checkOTPDTO) {
        Optional<OTP> otp = otpRepository.findByOtpEquals(checkOTPDTO.getOtp());
        if (otp.isPresent()){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public UserProfileDTO getUserByID(String userID) {
        Optional<User> byId = userRepository.findById(userID);
        if(byId.isPresent()){
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            User user = byId.get();
            userProfileDTO.setId(user.getId());
            userProfileDTO.setEmail(user.getEmail());
            userProfileDTO.setName(user.getName());
            userProfileDTO.setAddress(user.getAddress());
            userProfileDTO.setContact(user.getContact());
            userProfileDTO.setUserProfilePictureURL(user.getProfilePicture());
            userProfileDTO.setHarvestDTOS(harvestService.getAllHarvestsByFarmerID(userID));
            return userProfileDTO;
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        Optional<User> byId = userRepository.findById(userUpdateDTO.getId());
        if(byId.isPresent()){
            User user = byId.get();
            user.setEmail(userUpdateDTO.getEmail());
            user.setName(userUpdateDTO.getName());
            user.setAddress(userUpdateDTO.getAddress());
            user.setContact(userUpdateDTO.getContact());
            user.setProfilePicture(userUpdateDTO.getProfilePicture());
            userRepository.save(user);
            return true;
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Boolean passwordChange(PasswordChangeDTO passwordChangeDTO) {
        Optional<User> userByEmailEquals = userRepository.findUserByEmailEquals(passwordChangeDTO.getEmail());
        if(userByEmailEquals.isPresent() && passwordEncoder.matches(passwordChangeDTO.getOldPassword(),userByEmailEquals.get().getPassword())){
            userByEmailEquals.get().setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
            userRepository.save(userByEmailEquals.get());
            return true;
        }else {
            log.error("user not in database or old password is wrong!!!!!");
            return false;
        }
    }
}
