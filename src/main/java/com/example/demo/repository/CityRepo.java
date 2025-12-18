package com.example.demo.repository;

import com.example.demo.entity.City;
import com.example.demo.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {

    List<City> findByProvince(Province province);

    City findByCityName(String cityName);
}
