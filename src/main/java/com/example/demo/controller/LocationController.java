package com.example.demo.controller;



import com.example.demo.dto.CityDTO;
import com.example.demo.dto.DistrictDTO;
import com.example.demo.dto.ProvinceDTO;
import com.example.demo.dto.VillageDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.District;
import com.example.demo.entity.Province;
import com.example.demo.entity.Village;
import com.example.demo.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class LocationController extends BaseController {
    private LocationService locationService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllProvince")
    public ResponseEntity<?> getAllProvince() {
        List<ProvinceDTO> listProv = locationService.getAllProvince();
        return success(listProv);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllCity")
    public ResponseEntity<?> getAllCity(@RequestParam String province) {
        List<CityDTO> listCity = locationService.getAllCity(province);
        return success(listCity);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllDistrict")
    public ResponseEntity<?> getAllDistrict(@RequestParam String city) {
        List<DistrictDTO> listDistrict = locationService.getAllDistrict(city);
        return success(listDistrict);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllVillage")
    public ResponseEntity<?> getAllVillage(@RequestParam String district) {
        List<VillageDTO> listVillage = locationService.getAllVillage(district);
        return success(listVillage);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createProvince")
    public ResponseEntity<?> createProvince(@RequestParam String provinceName) {
        Province province = new Province();
        province.setProvinceName(provinceName);
        locationService.createProvince(province);
        return success(province);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createCity")
    public ResponseEntity<?> createCity(@RequestParam String cityName, @RequestParam @NonNull Long provinceId) {
        City city= new City();
        city.setCityName(cityName);
        return success (locationService.createCity(city, provinceId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createDistrict")
    public ResponseEntity<?> createDistrict(@RequestParam String districtName, @RequestParam @NonNull Long cityId) {
        District district= new District();
        district.setDistrictName(districtName);
        return success(locationService.createDistrict(district, cityId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createVillage")
    public ResponseEntity<?> createVillage(@RequestParam String villageName, @RequestParam @NonNull Long districtId) {
        Village village= new Village();
        village.setVillageName(villageName);
        return success(locationService.createVillage(village, districtId));
    }



}
