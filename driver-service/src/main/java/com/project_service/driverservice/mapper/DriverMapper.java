package com.project_service.driverservice.mapper;


import com.project_service.driverservice.dto.DriverRequest;
import com.project_service.driverservice.dto.DriverResponse;
import com.project_service.driverservice.entity.Driver;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toDriver(DriverRequest driverRequest);

    DriverResponse toDriverResponse(Driver driver);

    List<DriverResponse> toDriverResponseList(List<Driver> drivers);
}
