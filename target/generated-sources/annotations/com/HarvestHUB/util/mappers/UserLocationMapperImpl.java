package com.HarvestHUB.util.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T12:28:33+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserLocationMapperImpl implements UserLocationMapper {

    @Override
    public List<UserLocationDTO> EntityListToDTOList(List<UserLocation> userLocationList) {
        if ( userLocationList == null ) {
            return null;
        }

        List<UserLocationDTO> list = new ArrayList<UserLocationDTO>( userLocationList.size() );
        for ( UserLocation userLocation : userLocationList ) {
            list.add( userLocationToUserLocationDTO( userLocation ) );
        }

        return list;
    }

    protected UserLocationDTO userLocationToUserLocationDTO(UserLocation userLocation) {
        if ( userLocation == null ) {
            return null;
        }

        UserLocationDTO userLocationDTO = new UserLocationDTO();

        userLocationDTO.setId( userLocation.getId() );
        userLocationDTO.setUserID( userLocation.getUserID() );
        userLocationDTO.setLongitude( userLocation.getLongitude() );
        userLocationDTO.setLatitude( userLocation.getLatitude() );
        userLocationDTO.setLocationName( userLocation.getLocationName() );
        userLocationDTO.setOrganicPercentage( userLocation.getOrganicPercentage() );
        userLocationDTO.setGlassPercentage( userLocation.getGlassPercentage() );
        userLocationDTO.setPlasticPercentage( userLocation.getPlasticPercentage() );
        userLocationDTO.setEwastePercentage( userLocation.getEwastePercentage() );
        userLocationDTO.setDate( userLocation.getDate() );
        userLocationDTO.setCollectStatus( userLocation.isCollectStatus() );
        userLocationDTO.setCollectDate( userLocation.getCollectDate() );
        userLocationDTO.setDriverRouteAddedStatus( userLocation.isDriverRouteAddedStatus() );

        return userLocationDTO;
    }
}
