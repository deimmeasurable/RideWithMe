package com.example.ridewithme.services;

import com.example.ridewithme.dto.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;
import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;
import com.example.ridewithme.exception.UserNotFoundException;
import com.example.ridewithme.models.User;
import com.example.ridewithme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setLastName(userRequest.getLastName());
        user.setFirstName(userRequest.getFirstName());

       User savedUser= userRepository.save(user);

       UserResponse userResponse= new UserResponse();
       userResponse.setEmail(savedUser.getEmail());
       userResponse.setLastName(savedUser.getLastName());
       userResponse.setFirstName(savedUser.getFirstName());


        return userResponse;
    }

    @Override
    public UserUpdateDetailsResponse userCanUpdateDetails(UserUpdateDetailsRequest userUpdateDetailsRequest) {
        User foundUser = userRepository.findById(userUpdateDetailsRequest.getId()).orElseThrow(
                ()->  new UserNotFoundException("user not found")
        );
        if(!Objects.equals(userUpdateDetailsRequest.getEmail(), foundUser.getEmail())){
            throw new  UserNotFoundException("user with this email not found ");

        }

        return null;
    }
}
