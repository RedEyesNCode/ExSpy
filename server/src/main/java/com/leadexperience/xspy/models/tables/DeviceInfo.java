package com.leadexperience.xspy.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeviceInfo {

    @Id
    @SequenceGenerator(name = "victim_device_info", sequenceName = "victim_device_info",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "victim_device_info")
    private Long id;

    private String name;

    private ArrayList<String> deviceInfo;


}
