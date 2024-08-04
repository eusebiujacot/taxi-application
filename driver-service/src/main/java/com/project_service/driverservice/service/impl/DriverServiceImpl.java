package com.project_service.driverservice.service.impl;

import com.project_service.driverservice.dto.DriverRequest;
import com.project_service.driverservice.dto.DriverResponse;
import com.project_service.driverservice.entity.Driver;
import com.project_service.driverservice.exception.DriverNotFoundException;
import com.project_service.driverservice.mapper.DriverMapper;
import com.project_service.driverservice.repository.DriverRepository;
import com.project_service.driverservice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    @Transactional
    public String createDriver(DriverRequest driverRequest) {
        Driver driver = driverMapper.toDriver(driverRequest);
        driverRepository.save(driver);
        return driver.getId().toString();
    }

    @Override
    @Transactional
    public DriverResponse findDriver(String id){
        Driver driver = driverRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DriverNotFoundException(String.format("Driver with id %s not found", id)));
        return driverMapper.toDriverResponse(driver);
    }

    @Override
    @Transactional
    public List<DriverResponse> findAllDrivers(){
        List<Driver> drivers = driverRepository.findAll();
        return driverMapper.toDriverResponseList(drivers);
    }
}
