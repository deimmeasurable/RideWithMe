package com.example.ridewithme.services;


import com.example.ridewithme.dto.request.DriverRequest;
import com.example.ridewithme.dto.request.DriverRequestUpdate;
import com.example.ridewithme.dto.response.DriverResponse;
import com.example.ridewithme.exception.DriverNotFoundException;
import com.example.ridewithme.exception.UserNotFoundException;
import com.example.ridewithme.models.Driver;
import com.example.ridewithme.models.User;
import com.example.ridewithme.repository.DriverRepository;
import com.example.ridewithme.repository.TripRepository;
import com.example.ridewithme.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class DriverServiceImpl implements DriverService {

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    private DriverRepository    driverRepository;

    @Autowired

    private TripRepository  tripRepository;


    @Autowired

    private UserRepository  userRepository;

    @Override
    public DriverResponse createADriver(DriverRequest request) {
        Driver  driver = new Driver();
        driver.setId(request.getId());
        driver.setLastName(request.getLastName());
        driver.setFirstName(request.getFirstName());
        driver.setPassword(request.getPassword());
        driver.setEmail(request.getEmail());
        driver.setPhoneNumber(request.getPhoneNumber());

        driverRepository.save(driver);
log.info("driver:"+ driver);
        DriverResponse  driverResponse=new DriverResponse();
        driverResponse.setMessage("user with tis mail created successfully");
        driverResponse.setEmail(driver.getEmail());

        return driverResponse;


    }

    @Override
    public DriverResponse driverCanUpdateDetailS(DriverRequestUpdate driverRequestUpdate) {
        Driver  foundDriver=driverRepository.findDriverByEmail(driverRequestUpdate.getEmail())
                .orElseThrow(()->new DriverNotFoundException("driver with this email not found"));

        if(foundDriver.getEmail().equals(driverRequestUpdate.getEmail())){
            foundDriver.setEmail(driverRequestUpdate.getEmail());
            foundDriver.setPassword(driverRequestUpdate.getPassword());
            foundDriver.setFirstName(driverRequestUpdate.getFirstName());
            foundDriver.setLastName(driverRequestUpdate.getLastName());
            foundDriver.setPhoneNumber(driverRequestUpdate.getPhoneNumber());

        }
        driverRepository.save(foundDriver);

        DriverResponse  driverResponse=new DriverResponse();
        driverResponse.setEmail(foundDriver.getEmail());
        driverResponse.setMessage("driver details update  successfully");


        return driverResponse;
    }



}
