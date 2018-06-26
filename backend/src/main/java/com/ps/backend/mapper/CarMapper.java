package com.ps.backend.mapper;

import com.ps.backend.entity.Car;
import com.ps.common.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CarMapper {
    CarDTO toDTO(Car car);
}
