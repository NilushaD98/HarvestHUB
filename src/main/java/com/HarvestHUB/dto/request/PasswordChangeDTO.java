package com.HarvestHUB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordChangeDTO {

    private String email;
    private String oldPassword;
    private String newPassword;
}
