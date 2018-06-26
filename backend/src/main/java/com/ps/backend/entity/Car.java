package com.ps.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @ManyToMany
    private List<User> drivers;

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<User> drivers) {
        this.drivers = drivers;
    }
}
