package com.project_service.driverservice.dto;

import lombok.Data;

@Data
public class DriverRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String zip;
}
