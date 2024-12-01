package com.HarvestHUB.controller;

import com.HarvestHUB.dto.request.AddHarvestDTO;
import com.HarvestHUB.dto.request.SearchHarvestDTO;
import com.HarvestHUB.dto.request.UpdateHarvestStatusDTO;
import com.HarvestHUB.dto.response.HarvestDTO;
import com.HarvestHUB.service.HarvestService;
import com.HarvestHUB.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/harvesthub/api/v1/harvest/")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;

    @PostMapping("addHarvest")
    public ResponseEntity<StandardResponse> addHarvest(@RequestBody AddHarvestDTO addHarvestDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Harvest Added Status : ",harvestService.addHarvest(addHarvestDTO)), HttpStatus.CREATED
        );
    }
    @GetMapping("getAllHarvestsByFarmerID")
    public ResponseEntity<StandardResponse> getAllHarvestsByFarmerID(@RequestParam("farmerId")String farmerId){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.getAllHarvestsByFarmerID(farmerId)),HttpStatus.OK
        );
    }
    @PutMapping("updateHarvest")
    public ResponseEntity<StandardResponse> updateHarvest(@RequestBody HarvestDTO harvestDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.updateHarvest(harvestDTO)),HttpStatus.OK
        );
    }
    @DeleteMapping("deleteHarvest")
    public ResponseEntity<StandardResponse> deleteHarvest(@RequestParam("harvestID") String harvestID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.deleteHarvest(harvestID)),HttpStatus.OK
        );
    }
    @PutMapping("updateHarvestStatus")
    public ResponseEntity<StandardResponse> updateHarvestStatus(@RequestBody UpdateHarvestStatusDTO updateHarvestStatusDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.updateHarvestStatus(updateHarvestStatusDTO)),HttpStatus.OK
        );
    }
    @GetMapping("getAllHarvest")
    public ResponseEntity<StandardResponse> getAllHarvest(){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.getAllHarvest()),HttpStatus.OK
        );
    }
    @PostMapping("searchHarvests")
    public ResponseEntity<StandardResponse> searchHarvests(@RequestBody SearchHarvestDTO searchHarvestDTO) {
        return new ResponseEntity<StandardResponse>(
               new StandardResponse(200,"",harvestService.searchHarvests(searchHarvestDTO)),HttpStatus.OK
        );
    }
    @GetMapping("getHarvestByID")
    public ResponseEntity<StandardResponse> getHarvestByID(@RequestParam("harvestID")String harvestID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.getHarvestByID(harvestID)),HttpStatus.OK
        );
    }
    @GetMapping("farmersOrders")
    public ResponseEntity<StandardResponse> farmersOrders(@RequestParam("farmerID")String farmerID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",harvestService.farmersOrders(farmerID)),HttpStatus.OK
        );
    }
    @PutMapping("updateShippedStatus")
    public ResponseEntity<StandardResponse> updateShippedStatus(@RequestParam("orderID")String orderID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Shipped Status Updated",harvestService.updateShippedStatus(orderID)),HttpStatus.OK
        );
    }
}
