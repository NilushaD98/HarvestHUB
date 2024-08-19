package com.HarvestHUB.controller;

import com.HarvestHUB.dto.request.AuthenticationDTO;
import com.HarvestHUB.dto.response.AuthenticationSuccessDTO;
import com.HarvestHUB.service.AuthService;
import com.HarvestHUB.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/harvesthub/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<StandardResponse> login(@RequestBody AuthenticationDTO authenticationDTO){
        AuthenticationSuccessDTO authResponseDTO = authService.authenticate(authenticationDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Auth Response : ", authResponseDTO), HttpStatus.OK
        );
    }
}
