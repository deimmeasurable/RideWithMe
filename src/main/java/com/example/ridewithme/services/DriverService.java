package com.example.ridewithme.services;


import com.example.ridewithme.dto.request.DriverRequest;
import com.example.ridewithme.dto.request.DriverRequestUpdate;
import com.example.ridewithme.dto.response.DriverResponse;

public interface DriverService {


    DriverResponse createADriver(DriverRequest request);

    DriverResponse driverCanUpdateDetailS(DriverRequestUpdate driverRequestUpdate);


}
