package com.HarvestHUB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationSuccessDTO {

    private String user_id;
    private String user_role;
    private String access_token;
    private String refresh_token;
}
