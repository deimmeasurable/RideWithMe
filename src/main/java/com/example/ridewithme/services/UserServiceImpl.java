package com.example.ridewithme.services;

import com.example.ridewithme.dto.response.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;
import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;
import com.example.ridewithme.exception.UserAlreadyExistsException;
import com.example.ridewithme.exception.UserNotFoundException;
import com.example.ridewithme.models.User;
import com.example.ridewithme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        if(checkIfUserAlreadyExists(userRequest.getEmail())){
            throw new UserAlreadyExistsException("user already exist");
        }
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
                () -> new UserNotFoundException("user not found")
        );
        if(!Objects.equals(userUpdateDetailsRequest.getEmail(), foundUser.getEmail())){
            throw new  UserNotFoundException("user with this email not found ");

        }

            foundUser.setFirstName(userUpdateDetailsRequest.getFirstName());
            foundUser.setLastName(userUpdateDetailsRequest.getLastName());
            foundUser.setPassword(userUpdateDetailsRequest.getPassword());

            userRepository.save(foundUser);

        UserUpdateDetailsResponse userUpdateDetailsResponse =  UserUpdateDetailsResponse.builder()
                .message("details successfully updated")
                .email(userUpdateDetailsRequest.getEmail())
                .build();



        return userUpdateDetailsResponse;
    }

    @Override
    public List<User> getListOfUser() {
        return userRepository.findAll();
    }

    @Override
    public User getAUserById(long l) {
        User    founduser=userRepository.findById(l).orElseThrow(()->new UserNotFoundException("user  id  not found"));

        return founduser;
    }

    private boolean checkIfUserAlreadyExists( String email){
return userRepository.findUserByEmail(email).isPresent();


    }

}
