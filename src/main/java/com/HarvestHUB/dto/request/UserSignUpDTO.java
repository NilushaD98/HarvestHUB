package com.HarvestHUB.dto.request;

import com.HarvestHUB.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpDTO {

        private String email;
        private String name;
        private String address;
        private String contact;
        private ROLE role;
        private String password;
        private String userProfilePictureURL;

}
