package com.example.demo.service;

import com.example.demo.dto.CityDTO;
import com.example.demo.dto.DistrictDTO;
import com.example.demo.dto.ProvinceDTO;
import com.example.demo.dto.VillageDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.District;
import com.example.demo.entity.Province;
import com.example.demo.entity.Village;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.helper.ApiResponse;
import com.example.demo.repository.CityRepo;
import com.example.demo.repository.DistrictRepo;
import com.example.demo.repository.ProvinceRepo;
import com.example.demo.repository.VillageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final ProvinceRepo provinceRepo;
    private final CityRepo cityRepo;
    private final DistrictRepo districtRepo;
    private final VillageRepo villageRepo;

    /* ===================== GET ===================== */

    public List<ProvinceDTO> getAllProvince() {
        return provinceRepo.findAll()
                .stream()
                .map(p -> new ProvinceDTO(
                        p.getProvinceId(),
                        p.getProvinceName()
                ))
                .toList();
    }

    public List<CityDTO> getAllCity(String provinceName) {
        Province province = provinceRepo.findByProvinceName(provinceName);
        if (province == null) {
            throw new ResourceNotFound("Province not found");
        }

        return cityRepo.findByProvince(province)
                .stream()
                .map(c -> new CityDTO(
                        c.getCityId(),
                        c.getCityName(),
                        province.getProvinceId()
                ))
                .toList();
    }

    public List<DistrictDTO> getAllDistrict(String cityName) {
        City city = cityRepo.findByCityName(cityName);
        if (city == null) {
            throw new ResourceNotFound("City not found");
        }

        return districtRepo.findByCity(city)
                .stream()
                .map(d -> new DistrictDTO(
                        d.getDistrictId(),
                        d.getDistrictName(),
                        city.getCityId()
                ))
                .toList();
    }

    public List<VillageDTO> getAllVillage(String districtName) {
        District district = districtRepo.findByDistrictName(districtName);
        if (district == null) {
            throw new ResourceNotFound("District not found");
        }

        return villageRepo.findByDistrict(district)
                .stream()
                .map(v -> new VillageDTO(
                        v.getVillageId(),
                        v.getVillageName(),
                        district.getDistrictId()
                ))
                .toList();
    }

    /* ===================== CREATE ===================== */

    public ProvinceDTO createProvince(Province province) {
        Province saved = provinceRepo.save(province);
        return new ProvinceDTO(
                saved.getProvinceId(),
                saved.getProvinceName()
        );
    }

    public CityDTO createCity(City city, Long provinceId) {
        Province province = provinceRepo.findById(provinceId)
                .orElseThrow(() -> new ResourceNotFound("Province not found"));

        city.setProvince(province);
        City saved = cityRepo.save(city);

        return new CityDTO(
                saved.getCityId(),
                saved.getCityName(),
                provinceId
        );
    }

    public DistrictDTO createDistrict(District district, Long cityId) {
        City city = cityRepo.findById(cityId)
                .orElseThrow(() -> new ResourceNotFound("City not found"));

        district.setCity(city);
        District saved = districtRepo.save(district);

        return new DistrictDTO(
                saved.getDistrictId(),
                saved.getDistrictName(),
                cityId
        );
    }

    public VillageDTO createVillage(Village village, Long districtId) {
        District district = districtRepo.findById(districtId)
                .orElseThrow(() -> new ResourceNotFound("District not found"));

        village.setDistrict(district);
        Village saved = villageRepo.save(village);

        return new VillageDTO(
                saved.getVillageId(),
                saved.getVillageName(),
                districtId
        );
    }
}
