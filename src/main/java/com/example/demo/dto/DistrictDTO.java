package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DistrictDTO {
    private Long districtId;
    private String districtName;
    private Long cityId;
}
