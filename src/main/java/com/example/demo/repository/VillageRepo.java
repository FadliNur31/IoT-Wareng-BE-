package com.example.demo.repository;

import com.example.demo.entity.District;
import com.example.demo.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VillageRepo extends JpaRepository<Village, Long> {
    Village findByVillageName(String villageName);

    List<Village> findByDistrict(District district);

    Village findByVillageId(Long villageId);
}
