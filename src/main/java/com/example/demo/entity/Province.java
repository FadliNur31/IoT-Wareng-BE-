package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long provinceId;

    @Column(nullable = false)
    private String provinceName;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private java.util.List<City> cityList;
}
