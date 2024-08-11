package com.project_service.driverservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DriverResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String zip;
    private String licenseNumber;
    private UUID vehicleId;
    private Double rating;
    private Boolean isActive;
}
