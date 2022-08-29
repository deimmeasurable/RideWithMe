package com.example.ridewithme.services;

import com.example.ridewithme.dto.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;

import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;
import com.example.ridewithme.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceImplTest {

@Autowired
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService= new UserServiceImpl(userRepository);

    }
    @Test
    void TestThatUserCanBeCreated(){
        UserRequest userRequest= new UserRequest();
        userRequest.setFirstName("tola");
        userRequest.setLastName("shola");
        userRequest.setPassword("gratedel");
        userRequest.setEmail("graetedsite@gmail.com");

        UserResponse userResponse=userService.createUser(userRequest);

        assertEquals(userResponse.getEmail(),"graetedsite@gmail.com");
        assertEquals(userResponse.getFirstName(),"tola");
        assertEquals(userResponse.getLastName(),"shola");


    }
    @Test
    void testThatUserCanUpdateDetail(){
        UserRequest userRequest= new UserRequest();
        userRequest.setFirstName("tola");
        userRequest.setLastName("shola");
        userRequest.setPassword("gratedel");
        userRequest.setEmail("graetedsite@gmail.com");
        userService.createUser(userRequest);

        UserUpdateDetailsRequest userUpdateDetailsRequest = new UserUpdateDetailsRequest();
        userUpdateDetailsRequest.setId(2L);
        userUpdateDetailsRequest.setEmail("graetedsite@gmail.com");
        userUpdateDetailsRequest.setLastName("titi");
        userUpdateDetailsRequest.setFirstName("tope");
        userUpdateDetailsRequest.setPassword("gratedel");

        UserUpdateDetailsResponse response=userService.userCanUpdateDetails(userUpdateDetailsRequest);

        assertEquals()


    }
}