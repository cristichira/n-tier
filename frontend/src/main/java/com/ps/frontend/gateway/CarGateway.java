package com.ps.frontend.gateway;

import com.ps.common.dto.CarDTO;

import java.util.List;

public interface CarGateway {

    CarDTO findById(Long id);

    List<CarDTO> findAll();

    Long save(CarDTO carDTO);
}
