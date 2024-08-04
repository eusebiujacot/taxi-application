package com.project_service.driverservice.controller;

import com.project_service.driverservice.dto.DriverRequest;
import com.project_service.driverservice.dto.DriverResponse;
import com.project_service.driverservice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createDriver(@RequestBody DriverRequest driverRequest) {
        return driverService.createDriver(driverRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DriverResponse> findAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DriverResponse findDriver(@PathVariable String id) {
        return driverService.findDriver(id);
    }


}

