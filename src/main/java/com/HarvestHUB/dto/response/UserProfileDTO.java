package com.HarvestHUB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDTO {

    private String id;
    private String email;
    private String name;
    private String address;
    private String contact;
    private String userProfilePictureURL;
    private List<HarvestDTO> harvestDTOS;
}
