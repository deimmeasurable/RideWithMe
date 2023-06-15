package com.example.ridewithme.services;

import com.example.ridewithme.dto.request.*;
import com.example.ridewithme.dto.response.CheckUserLocationResponse;
import com.example.ridewithme.dto.response.UserPayForTripResponse;
import com.example.ridewithme.dto.response.UserTripResponse;
import com.example.ridewithme.exception.DriverNotFoundException;
import com.example.ridewithme.exception.UserNotFoundException;
import com.example.ridewithme.exception.UserTripException;
import com.example.ridewithme.models.Driver;
import com.example.ridewithme.models.Trip;
import com.example.ridewithme.models.User;
import com.example.ridewithme.repository.DriverRepository;
import com.example.ridewithme.repository.TripRepository;
import com.example.ridewithme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {


    @Autowired
  private   UserRepository  userRepository;

    public TripServiceImpl(UserRepository userRepository,DriverRepository   driverRepository,TripRepository tripRepository) {
        this.userRepository = userRepository;
        this.driverRepository=driverRepository;
        this.tripRepository=tripRepository;
    }

    @Autowired
    private DriverRepository    driverRepository;

    @Autowired
    private TripRepository tripRepository;



    @Override
    public UserTripResponse searchForlocation(UserTripRequest userTripRequest) {

        Trip    trip=new Trip();
        UserTripResponse    userTripResponse=new UserTripResponse();

if(!userTripRequest.isTripStatus()) {
    throw new UserTripException("user is yet to confirm trip");

}

    trip.setEmail(userTripRequest.getEmail());
    trip.setLocation(userTripRequest.getLocation());
    trip.setDestination(userTripRequest.getDestination());
        trip.setTripStatus(true);


        userTripResponse.setEmail(userTripRequest.getEmail());
    userTripResponse.setLocation(userTripRequest.getLocation());
    userTripResponse.setDestination(userTripRequest.getDestination());
    userTripResponse.setTripStatus(userTripRequest.isTripStatus());

        return userTripResponse;
    }
    @Override
    public CheckUserLocationResponse driverCanViewLocationOfUser(CheckUserLocation userLocation) {
        User foundUser = userRepository.findUserByEmail(userLocation.getUserEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));
        Driver foundDriver = driverRepository.findDriverByEmail(userLocation.getDriverEmail()).orElseThrow(()-> new DriverNotFoundException("driver not found"));


        tripRepository.findTripByEmail(userLocation.getDriverEmail());

       CheckUserLocationResponse   checkUserLocation=new CheckUserLocationResponse();
       checkUserLocation.setUserEmail(foundUser.getEmail());
       checkUserLocation.setDriverEmail(foundDriver.getEmail());
       checkUserLocation.setLocation(userLocation.getLocation());
       checkUserLocation.setMessage("user location confirm");



        return checkUserLocation;
    }

    @Override
    public UserPayForTripResponse tripCanBePaid(UserPayForTripRequest userPayForTripRequest) {
        User foundUser = userRepository.findUserByEmail(userPayForTripRequest.getUserEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));
        Driver foundDriver = driverRepository.findDriverByEmail(userPayForTripRequest.getDriverEmail()).orElseThrow(()-> new DriverNotFoundException("driver not found"));

        tripRepository.findTripByEmail(userPayForTripRequest.getDriverEmail());

        UserPayForTripRequest   request = new UserPayForTripRequest();
        request.setDriverEmail(foundDriver.getEmail());
        request.setUserEmail(foundUser.getEmail());
        request.setTripFee(userPayForTripRequest.getTripFee());

        UserPayForTripResponse  userResponse = new UserPayForTripResponse();
        userResponse.setAmount(request.getTripFee());
        userResponse.setMessage(foundDriver.getEmail() +  " trip fee paid");
        return userResponse;
    }

    @Override
    public CommissionAgentResponse commissionPaidByDriver(UserPayForTripRequest userPayForTripRequest,CommissionAgentRequest commissionAgentRequest) {
        User foundUser = userRepository.findUserByEmail(userPayForTripRequest.getUserEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));
        Driver foundDriver = driverRepository.findDriverByEmail(userPayForTripRequest.getDriverEmail()).orElseThrow(()-> new DriverNotFoundException("driver not found"));

        UserPayForTripResponse  userPayForTripResponse = tripCanBePaid(userPayForTripRequest);
        CommissionAgentRequest  commissionAgent  = new CommissionAgentRequest();
        commissionAgent.setUserEmail(userPayForTripRequest.getUserEmail());
        commissionAgent.setDriverEmail(userPayForTripRequest.getDriverEmail());
        commissionAgent.setCommissionAmount(commissionAgentRequest.getCommissionAmount());

        System.out.println(userPayForTripResponse.getAmount());
       double   commissionValue = userPayForTripResponse.getAmount()* commissionAgentRequest.getCommissionAmount();

       CommissionAgentResponse  commissionAgentResponse=new CommissionAgentResponse();

       commissionAgentResponse.setCommissionAmount(commissionValue);
       commissionAgentResponse.setEmail(userPayForTripRequest.getDriverEmail());

       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
        return commissionAgentResponse;
    }

}
