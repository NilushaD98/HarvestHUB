package com.HarvestHUB.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Username or password wrong")
public class BadCredentialException extends RuntimeException{
}
