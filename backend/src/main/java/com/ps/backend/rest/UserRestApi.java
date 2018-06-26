package com.ps.backend.rest;

import com.ps.common.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserRestApi {

    @GetMapping("/test")
    String testUser();

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<UserDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody UserDTO userDTO);
}
