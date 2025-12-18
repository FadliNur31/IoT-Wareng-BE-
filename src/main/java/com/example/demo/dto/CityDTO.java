package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityDTO {
    private Long cityId;
    private String cityName;
    private Long provinceId;
}
