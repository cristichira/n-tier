package com.ps.backend.rest.impl;

import com.ps.backend.rest.CarRestApi;
import com.ps.backend.service.CarService;
import com.ps.common.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarRestController implements CarRestApi {

    private final CarService carService;

    @Autowired
    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @Override
    public CarDTO findById(@PathVariable("id") Long id) {
        return carService.findById(id);
    }

    @Override
    public List<CarDTO> findAll() {
        return carService.findAll();
    }

    @Override
    public Long save(@RequestBody CarDTO carDTO) {
        return carService.save(carDTO);
    }
}
