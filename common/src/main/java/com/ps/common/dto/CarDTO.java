package com.ps.common.dto;

import java.util.List;

public class CarDTO extends NameIdDTO {
    private NameIdDTO owner;
    private List<NameIdDTO> drivers;

    public NameIdDTO getOwner() {
        return owner;
    }

    public void setOwner(NameIdDTO owner) {
        this.owner = owner;
    }

    public List<NameIdDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<NameIdDTO> drivers) {
        this.drivers = drivers;
    }
}
