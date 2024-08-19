package com.HarvestHUB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String id;
    private String email;
    private String name;
    private String address;
    private String contact;
    private String address_latitude;
    private String address_longitude;
    private String password;
    private String role;
    private String profilePicture;

}
