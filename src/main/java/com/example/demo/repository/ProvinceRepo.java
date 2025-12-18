package com.example.demo.repository;

import com.example.demo.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepo extends JpaRepository<Province, Long> {
    Province findByProvinceName(String provinceName);
}
