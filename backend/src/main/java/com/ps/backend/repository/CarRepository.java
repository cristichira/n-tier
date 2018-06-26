package com.ps.backend.repository;

import com.ps.backend.entity.Car;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long>{

    Car findByOwnerId(Long id);
}
