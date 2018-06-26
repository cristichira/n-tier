package com.ps.backend.rest;

import com.ps.common.dto.CarDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/car")
public interface CarRestApi {

    @GetMapping("/{id}")
    CarDTO findById(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<CarDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody CarDTO carDTO);
}
