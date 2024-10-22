package com.HarvestHUB.service.IMPL;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.collection.Order;
import com.HarvestHUB.collection.User;
import com.HarvestHUB.dto.request.AddHarvestDTO;
import com.HarvestHUB.dto.request.SearchHarvestDTO;
import com.HarvestHUB.dto.request.UpdateHarvestStatusDTO;
import com.HarvestHUB.dto.response.AllHarvestsDTO;
import com.HarvestHUB.dto.response.HarvestDTO;
import com.HarvestHUB.dto.response.OrderDTO;
import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.enums.MeasuringUnits;
import com.HarvestHUB.repo.HarvestRepository;
import com.HarvestHUB.repo.OrderRepository;
import com.HarvestHUB.repo.UserRepository;
import com.HarvestHUB.service.HarvestService;
import com.HarvestHUB.util.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HarvestServiceIMPL implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private Date getCurrentDateInUTC() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        return Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
    }

    private String formatToLocalTime(Date date) {
        ZonedDateTime utcZoned = date.toInstant().atZone(ZoneId.of("UTC"));
        ZonedDateTime localZoned = utcZoned.withZoneSameInstant(ZoneId.of("Asia/Colombo"));
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localZoned);
    }
    @Override
    public Boolean addHarvest(AddHarvestDTO addHarvestDTO) {
        try {
            Harvest harvest = new Harvest(
                    addHarvestDTO.getFarmerId(),
                    addHarvestDTO.getType(),
                    addHarvestDTO.getMeasuringUnits(),
                    addHarvestDTO.getQuantity(),
                    addHarvestDTO.getPrice(),
                    addHarvestDTO.getAvailability(),
                    addHarvestDTO.getLocation(),
                    addHarvestDTO.getDescription(),
                    getCurrentDateInUTC()
            );
            harvest.setImageURL(addHarvestDTO.getImageURL());
            harvestRepository.save(harvest);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<HarvestDTO> getAllHarvestsByFarmerID(String farmerId) {
        List<Harvest> allByFarmerIdEquals = harvestRepository.findAllByFarmerIdEquals(farmerId);
        List<HarvestDTO> harvestDTOS = new ArrayList<>();
        allByFarmerIdEquals.forEach(
                (harvest) -> {
                    HarvestDTO harvestDTO = new HarvestDTO();
                    harvestDTO.setHarvestId(harvest.getHarvestId());
                    harvestDTO.setFarmerId(harvest.getFarmerId());
                    harvestDTO.setType(harvest.getType());
                    harvestDTO.setMeasuringUnits(harvest.getMeasuringUnits());
                    harvestDTO.setQuantity(harvest.getQuantity());
                    harvestDTO.setPrice(harvest.getPrice());
                    harvestDTO.setAvailability(harvest.getAvailability());
                    harvestDTO.setLocation(harvest.getLocation());
                    harvestDTO.setDescription(harvest.getDescription());
                    harvestDTO.setImageURL(harvest.getImageURL());
                    harvestDTO.setCreatedAt(formatToLocalTime(harvest.getCreatedAt()));
                    harvestDTOS.add(harvestDTO);
                }
        );
        return harvestDTOS;
    }

    @Override
    public Boolean updateHarvest(HarvestDTO harvestDTO) {
        Optional<Harvest> byId = harvestRepository.findById(harvestDTO.getHarvestId());
        Harvest harvest = byId.get();
        harvest.setType(harvestDTO.getType());
        harvest.setMeasuringUnits(harvestDTO.getMeasuringUnits());
        harvest.setQuantity(harvestDTO.getQuantity());
        harvest.setPrice(harvestDTO.getPrice());
        harvest.setAvailability(harvestDTO.getAvailability());
        harvest.setLocation(harvestDTO.getLocation());
        harvest.setDescription(harvestDTO.getDescription());
        harvest.setCreatedAt(getCurrentDateInUTC());
        harvestRepository.save(harvest);
        return true;
    }

    @Override
    public Boolean deleteHarvest(String harvestID) {
        try {
            harvestRepository.deleteById(harvestID);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateHarvestStatus(UpdateHarvestStatusDTO updateHarvestStatusDTO) {
        try {
            Optional<Harvest> byId = harvestRepository.findById(updateHarvestStatusDTO.getHarvestID());
            byId.get().setAvailability(updateHarvestStatusDTO.getAvailableStatus());
            harvestRepository.save(byId.get());
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<AllHarvestsDTO> getAllHarvest() {
        List<Harvest> allByOrderByCreatedAtDesc = harvestRepository.findAllByOrderByCreatedAtDesc();
        List<AllHarvestsDTO> allHarvestsDTOS = new ArrayList<>();
        allByOrderByCreatedAtDesc.forEach(
                (harvest) ->{
                    User user = userRepository.findById(harvest.getFarmerId()).get();
                    AllHarvestsDTO allHarvestsDTO= new AllHarvestsDTO(
                            harvest.getHarvestId(),
                            user.getId(),
                            user.getEmail(),
                            user.getName(),
                            user.getAddress(),
                            user.getContact(),
                            user.getProfilePicture(),
                            harvest.getType(),
                            harvest.getMeasuringUnits(),
                            harvest.getQuantity(),
                            harvest.getPrice(),
                            harvest.getAvailability(),
                            harvest.getLocation(),
                            harvest.getDescription(),
                            harvest.getImageURL(),
                            formatToLocalTime(harvest.getCreatedAt())
                    );
                    allHarvestsDTOS.add(allHarvestsDTO);
                }
        );
        return allHarvestsDTOS;
    }

    @Override
    public List<AllHarvestsDTO> searchHarvests(SearchHarvestDTO searchHarvestDTO) {
        List<AllHarvestsDTO> allHarvestsDTOS = new ArrayList<>();
        List<Harvest> harvests = harvestRepository.searchHarvests(searchHarvestDTO.getType(), searchHarvestDTO.getLocation(), searchHarvestDTO.getMinPrice(), searchHarvestDTO.getMaxPrice(),searchHarvestDTO.getAvailableStatus());
        harvests.forEach(
                (harvest)->{
                    User user = userRepository.findById(harvest.getFarmerId()).get();
                    AllHarvestsDTO allHarvestsDTO= new AllHarvestsDTO(
                            harvest.getHarvestId(),
                            user.getId(),
                            user.getEmail(),
                            user.getName(),
                            user.getAddress(),
                            user.getContact(),
                            user.getProfilePicture(),
                            harvest.getType(),
                            harvest.getMeasuringUnits(),
                            harvest.getQuantity(),
                            harvest.getPrice(),
                            harvest.getAvailability(),
                            harvest.getLocation(),
                            harvest.getDescription(),
                            harvest.getImageURL(),
                            formatToLocalTime(harvest.getCreatedAt())
                    );
                    allHarvestsDTOS.add(allHarvestsDTO);
                }
        );
        return allHarvestsDTOS;
    }

    @Override
    public Harvest getHarvestByID(String harvestID) {
        return harvestRepository.findById(harvestID).get();
    }

    @Override
    public List<OrderDTO> farmersOrders(String farmerID) {
        List<Order> orderList = orderRepository.findByFarmerIDEquals(farmerID);
        return orderMapper.EntityListTODTOList(orderList);
    }

    @Override
    public Boolean updateShippedStatus(String orderID) {
        Optional<Order> byId = orderRepository.findById(orderID);
        byId.get().setShippingStatus(true);
        orderRepository.save(byId.get());
        return true;
    }
}
