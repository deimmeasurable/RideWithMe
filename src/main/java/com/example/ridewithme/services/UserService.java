package com.example.ridewithme.services;

import com.example.ridewithme.dto.response.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;
import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;
import com.example.ridewithme.models.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserUpdateDetailsResponse userCanUpdateDetails(UserUpdateDetailsRequest userUpdateDetailsRequest);
    List<User>getListOfUser();

    User getAUserById(long l);
}
