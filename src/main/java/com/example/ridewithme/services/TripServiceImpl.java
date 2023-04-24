package com.example.ridewithme.services;

import com.example.ridewithme.dto.request.CheckUserLocation;
import com.example.ridewithme.dto.request.UserTripRequest;
import com.example.ridewithme.dto.response.CheckUserLocationResponse;
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

}