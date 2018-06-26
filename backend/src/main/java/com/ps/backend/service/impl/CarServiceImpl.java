package com.ps.backend.service.impl;

import com.ps.backend.entity.Car;
import com.ps.backend.mapper.CarMapper;
import com.ps.backend.repository.CarRepository;
import com.ps.backend.repository.UserRepository;
import com.ps.backend.service.CarService;
import com.ps.common.dto.CarDTO;
import com.ps.common.dto.NameIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarServiceImpl(CarMapper carMapper,
                          CarRepository carRepository,
                          UserRepository userRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CarDTO findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
        return carMapper.toDTO(car);
    }

    public List<CarDTO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(CarDTO carDTO) {
        Car car = carDTO.getId() != null ?
                carRepository.findById(carDTO.getId()).orElseThrow(EntityNotFoundException::new) : new Car();

        car.setName(carDTO.getName());
        car.setOwnerId(carDTO.getOwner().getId());
        car.setDrivers(userRepository.findAllById(carDTO.getDrivers()
                .stream()
                .map(NameIdDTO::getId)
                .collect(Collectors.toSet()))
        );

        return carRepository.save(car).getId();
    }
}
