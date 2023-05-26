package com.example.ridewithme.services;

import com.example.ridewithme.dto.response.UserPayForTripResponse;
import com.example.ridewithme.dto.request.*;

import com.example.ridewithme.dto.response.CheckUserLocationResponse;
import com.example.ridewithme.dto.response.UserTripResponse;
import com.example.ridewithme.exception.UserTripException;
import com.example.ridewithme.repository.DriverRepository;
import com.example.ridewithme.repository.TripRepository;
import com.example.ridewithme.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class TripServiceImplTest {



    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService   driverService;

    @Autowired
    private TripRepository  tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository    driverRepository;





    @BeforeEach
    void setUp() {
        tripService=new TripServiceImpl(userRepository,driverRepository,tripRepository);

    }
@Test

    void  pickALocationForTrip(){
        UserTripRequest userTripRequest =new UserTripRequest();
        userTripRequest.setEmail("graetedsite@gmail.com");
        userTripRequest.setLocation("folaAgoro rd,shomolu,lagos");
        userTripRequest.setDestination("olubunnmi street, onipanu");
        userTripRequest.setTripStatus(true);



        UserTripResponse userTripResponse=tripService.searchForlocation(userTripRequest);

        assertEquals(userTripResponse.getLocation(),"folaAgoro rd,shomolu,lagos");
        assertEquals(userTripResponse.getDestination(),"olubunnmi street, onipanu");
        assertTrue(userTripResponse.isTripStatus(), String.valueOf(true));



    }
    @Test
    void userCanCancelTrip(){
        UserTripRequest userTripRequest =new UserTripRequest();
        userTripRequest.setTripStatus(false);
        userTripRequest.setEmail("graetedsite@gmail.com");
        userTripRequest.setLocation("folaAgoro rd,shomolu,lagos");
        userTripRequest.setDestination("olubunnmi street, onipanu");
       
        assertThrows(UserTripException.class,()->tripService.searchForlocation(userTripRequest));


    }
    @Test
    void    testThatUserCanUserDeclineTrip(){

    }
    @Test
    void testThatDriverCanViewUserLocation(){
        UserRequest userRequest=new UserRequest();
        userRequest.setFirstName("tola");
        userRequest.setLastName("shola");
        userRequest.setPassword("gratedel");
        userRequest.setEmail("graetedsite@gmail.com");
        userService.createUser(userRequest);

        UserTripRequest userTripRequest =new UserTripRequest();
        userTripRequest.setEmail("graetedsite@gmail.com");
        userTripRequest.setLocation("folaAgoro rd,shomolu,lagos");
        userTripRequest.setDestination("olubunnmi street, onipanu");
        userTripRequest.setTripStatus(true);



        tripService.searchForlocation(userTripRequest);

        DriverRequest request=new DriverRequest();
        request.setId(1L);
        request.setPassword("drv345!");
        request.setEmail("tolurate@gmail.com");
        request.setFirstName("seyi");
        request.setLastName("olwuatolu");
        request.setPhoneNumber("08156478934");
        driverService.createADriver(request);






        UserTripResponse response=tripService.searchForlocation(userTripRequest);


        CheckUserLocation userLocation=new CheckUserLocation();

        userLocation.setDriverEmail("tolurate@gmail.com");
        userLocation.setUserEmail("graetedsite@gmail.com");

        userLocation.setLocation(userTripRequest.getLocation());

        CheckUserLocationResponse location=tripService.driverCanViewLocationOfUser(userLocation);

        assertEquals(location.getLocation(),"folaAgoro rd,shomolu,lagos");
        assertEquals(location.getDriverEmail(),"tolurate@gmail.com");
    }
    @Test
    void testThatTransportFeeCanBePay(){
        UserRequest userRequest=new UserRequest();
        userRequest.setFirstName("tola");
        userRequest.setLastName("shola");
        userRequest.setPassword("gratedel");
        userRequest.setEmail("graetedsite@gmail.com");
        userService.createUser(userRequest);

        UserTripRequest userTripRequest =new UserTripRequest();
        userTripRequest.setEmail("graetedsite@gmail.com");
        userTripRequest.setLocation("folaAgoro rd,shomolu,lagos");
        userTripRequest.setDestination("olubunnmi street, onipanu");
        userTripRequest.setTripStatus(true);



        tripService.searchForlocation(userTripRequest);

        DriverRequest request=new DriverRequest();
        request.setId(1L);
        request.setPassword("drv345!");
        request.setEmail("tolurate@gmail.com");
        request.setFirstName("seyi");
        request.setLastName("olwuatolu");
        request.setPhoneNumber("08156478934");
        driverService.createADriver(request);

        UserPayForTripRequest   userPayForTripRequest   =new UserPayForTripRequest();
        userPayForTripRequest.setUserEmail(userRequest.getEmail());
        userPayForTripRequest.setDriverEmail(request.getEmail());
        userPayForTripRequest.setTripFee(2000);

        UserPayForTripResponse response =   tripService.tripCanBePaid(userPayForTripRequest);

        assertEquals(response.getAmount(),2000);
        assertEquals(response.getMessage(),"tolurate@gmail.com trip fee paid");


    }
    @Test
    void testThatTwentyFivePercentPayForTripRequestCommission(){

    }

}