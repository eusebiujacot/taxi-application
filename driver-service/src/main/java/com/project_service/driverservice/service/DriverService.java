package com.project_service.driverservice.service;

import com.project_service.driverservice.dto.DriverRequest;
import com.project_service.driverservice.dto.DriverResponse;

import java.util.List;

public interface DriverService {

    String createDriver(DriverRequest driverRequest);

    DriverResponse findDriver(String id);

    List<DriverResponse> findAllDrivers();
}
