package com.example.demo.repository;

import com.example.demo.entity.City;
import com.example.demo.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {
    List<District> findByCity(City city);

    District findByDistrictName(String districtName);
}
