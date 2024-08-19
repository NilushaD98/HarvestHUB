package com.HarvestHUB.service;


import com.HarvestHUB.dto.request.AuthenticationDTO;
import com.HarvestHUB.dto.response.AuthenticationSuccessDTO;

public interface AuthService {
    AuthenticationSuccessDTO authenticate(AuthenticationDTO authenticationRequest);

//    AuthResponseDTO passwordChange(ChangePasswordDTO changePasswordDTO);
}
