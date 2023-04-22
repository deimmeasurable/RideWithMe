package com.example.ridewithme.services;

import com.example.ridewithme.dto.request.*;

import com.example.ridewithme.dto.response.DriverResponse;

import com.example.ridewithme.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DriverServiceImplTest {
    private DriverService   driverService;
    @Autowired

    private DriverRepository    driverRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @BeforeEach()
        void Setup(){
        driverService=new DriverServiceImpl(driverRepository);

    }
    @Test
    void testThatDriveCanBeCreated() {
        DriverRequest request=new DriverRequest();
        request.setId(1L);
        request.setPassword("drv345!");
        request.setEmail("tolurate@gmail.com");
        request.setFirstName("seyi");
        request.setLastName("olwuatolu");
        request.setPhoneNumber("08156478934");
        DriverResponse   response = driverService.createADriver(request);


        assertEquals(response.getEmail(),"tolurate@gmail.com");
        assertEquals(response.getMessage(),"user with tis mail created successfully");
    }
    @Test
    void testThatDriverCanUpdateDetails(){


        DriverRequest request=new DriverRequest();
        request.setId(1L);
        request.setPassword("drv345!");
        request.setEmail("tolurate@gmail.com");
        request.setFirstName("seyi");
        request.setLastName("olwuatolu");
        request.setPhoneNumber("08156478934");
         driverService.createADriver(request);

        DriverRequestUpdate driverRequestUpdate=new DriverRequestUpdate();
        driverRequestUpdate.setEmail("tolurate@gmail.com");
        driverRequestUpdate.setPassword("drv345!");
        driverRequestUpdate.setFirstName("seyi");
        driverRequestUpdate.setLastName("afolabi");
        driverRequestUpdate.setPhoneNumber("09160469323");
        DriverResponse response=   driverService.driverCanUpdateDetailS(driverRequestUpdate);

        assertEquals(response.getEmail(),"tolurate@gmail.com");
        assertEquals(response.getMessage(),"driver details update  successfully");
    }



}