package com.HarvestHUB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {

        private String id;
        private String email;
        private String name;
        private String address;
        private String contact;
        private String profilePicture;
}
