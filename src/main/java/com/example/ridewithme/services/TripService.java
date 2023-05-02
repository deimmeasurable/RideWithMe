package com.example.ridewithme.services;

import com.example.ridewithme.dto.request.CheckUserLocation;
;
import com.example.ridewithme.dto.request.UserPayForTripRequest;
import com.example.ridewithme.dto.request.UserTripRequest;
import com.example.ridewithme.dto.response.CheckUserLocationResponse;
import com.example.ridewithme.dto.response.UserPayForTripResponse;
import com.example.ridewithme.dto.response.UserTripResponse;

public interface TripService {

    UserTripResponse searchForlocation(UserTripRequest userTripRequest);

    CheckUserLocationResponse driverCanViewLocationOfUser(CheckUserLocation userLocation);

    UserPayForTripResponse tripCanBePaid(UserPayForTripRequest userPayForTripRequest);
}
