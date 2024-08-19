package com.HarvestHUB.repo;


import com.HarvestHUB.collection.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface OTPRepository extends MongoRepository<OTP,String> {

    void deleteOTPSByOtpExpirationTimeBefore(Date currentTime);
    Optional<OTP> findByOtpEquals(String otp);

}
