package com.ps.frontend.gateway.impl;

import com.ps.common.dto.UserDTO;
import com.ps.frontend.conf.RestProperties;
import com.ps.frontend.gateway.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class UserGatewayImpl implements UserGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/user";

    private final RestProperties restProperties;

    @Autowired
    public UserGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public String test() {
        LOGGER.info("Testing test() method, connection to backend api");
        String url = restProperties.getUrl() + URL + "/test";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    @Override
    public UserDTO findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        UserDTO user = restTemplate.getForObject(url, UserDTO.class);
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> forEntity = restTemplate.getForEntity(url, UserDTO[].class);
        UserDTO[] response = restTemplate.getForObject(url, UserDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public Long save(UserDTO userDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(userDTO);
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.postForObject(url, httpEntity, Long.class);
        return response;
    }
}
