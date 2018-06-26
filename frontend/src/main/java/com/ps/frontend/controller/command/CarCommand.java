package com.ps.frontend.controller.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class CarCommand {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long owner;
    private Set<Long> drivers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Set<Long> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Long> drivers) {
        this.drivers = drivers;
    }
}
