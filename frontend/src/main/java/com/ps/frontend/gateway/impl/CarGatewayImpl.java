package com.ps.frontend.gateway.impl;

import com.ps.common.dto.CarDTO;
import com.ps.frontend.conf.RestProperties;
import com.ps.frontend.gateway.CarGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class CarGatewayImpl implements CarGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarGatewayImpl.class);
    private final String URL = "/car";

    private final RestProperties restProperties;

    @Autowired
    public CarGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public CarDTO findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        CarDTO car = restTemplate.getForObject(url, CarDTO.class);
        return car;
    }

    @Override
    public List<CarDTO> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        CarDTO[] response = restTemplate.getForObject(url, CarDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public Long save(CarDTO carDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(carDTO);
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.postForObject(url, httpEntity, Long.class);
        return response;
    }
}
