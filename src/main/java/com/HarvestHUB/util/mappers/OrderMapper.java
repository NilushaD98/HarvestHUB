package com.HarvestHUB.util.mappers;

import com.HarvestHUB.collection.Order;
import com.HarvestHUB.dto.response.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "orderID", target = "_id")
    List<OrderDTO> EntityListTODTOList(List<Order> orderList);
}
