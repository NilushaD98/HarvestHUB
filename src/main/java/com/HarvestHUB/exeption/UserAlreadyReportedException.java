package com.HarvestHUB.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED,reason = "user email already reported in database")
public class UserAlreadyReportedException extends RuntimeException{
}
