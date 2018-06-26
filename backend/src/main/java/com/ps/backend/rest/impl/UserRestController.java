package com.ps.backend.rest.impl;

import com.ps.backend.rest.UserRestApi;
import com.ps.backend.service.UserService;
import com.ps.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController implements UserRestApi {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String testUser() {
        return userService.testService() + " and controller";
    }

    @Override
    public UserDTO findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @Override
    public Long save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
