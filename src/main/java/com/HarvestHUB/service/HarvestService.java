package com.HarvestHUB.service;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.dto.request.AddHarvestDTO;
import com.HarvestHUB.dto.request.UpdateHarvestStatusDTO;
import com.HarvestHUB.dto.response.AllHarvestsDTO;
import com.HarvestHUB.dto.response.HarvestDTO;

import java.util.List;

public interface HarvestService {
    Boolean addHarvest(AddHarvestDTO addHarvestDTO);

    List<HarvestDTO> getAllHarvestsByFarmerID(String farmerId);

    Boolean updateHarvest(HarvestDTO harvestDTO);

    Boolean deleteHarvest(String harvestID);

    Boolean updateHarvestStatus(UpdateHarvestStatusDTO updateHarvestStatusDTO);

    List<AllHarvestsDTO> getAllHarvest();
}