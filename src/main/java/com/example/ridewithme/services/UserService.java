package com.example.ridewithme.services;

import com.example.ridewithme.dto.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;
import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserUpdateDetailsResponse userCanUpdateDetails(UserUpdateDetailsRequest userUpdateDetailsRequest);
}
