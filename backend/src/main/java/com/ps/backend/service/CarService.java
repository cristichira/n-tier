package com.ps.backend.service;

import com.ps.common.dto.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO findById(Long id);

    List<CarDTO> findAll();

    Long save(CarDTO carDTO);
}
