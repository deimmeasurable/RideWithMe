package com.example.ridewithme.services;

import com.example.ridewithme.dto.response.UserResponse;
import com.example.ridewithme.dto.request.UserRequest;

import com.example.ridewithme.dto.request.UserUpdateDetailsRequest;
import com.example.ridewithme.dto.response.UserUpdateDetailsResponse;
import com.example.ridewithme.models.User;
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
        userUpdateDetailsRequest.setId(1L);
        userUpdateDetailsRequest.setEmail("graetedsite@gmail.com");
        userUpdateDetailsRequest.setLastName("titi");
        userUpdateDetailsRequest.setFirstName("tope");
        userUpdateDetailsRequest.setPassword("gratedel");

        UserUpdateDetailsResponse response=userService.userCanUpdateDetails(userUpdateDetailsRequest);

        assertEquals(response.getMessage(),"details successfully updated");
        assertEquals(response.getEmail(),"graetedsite@gmail.com");


    }
    @Test
    void TestThatMoreThaOneUserCanBeCreated(){
        UserRequest userRequest= new UserRequest();
        userRequest.setFirstName("tola");
        userRequest.setLastName("shola");
        userRequest.setPassword("gratedel");
        userRequest.setEmail("graetedsite@gmail.com");
        userService.createUser(userRequest);

        UserRequest userRequest2 = UserRequest.builder()
//                .id(3L)
                .email("lasers@gmail.com")
                .lastName("yinka")
                .firstName("funke")
                .password("prinky234")
                .build();
        userService.createUser(userRequest2);


       assertEquals(userRepository.count(),2);



    }
    @Test
    void testThatAUserCanBeFoundById(){
        UserRequest userRequest2 = UserRequest.builder()

                .email("lasers@gmail.com")
                .lastName("yinka")
                .firstName("funke")
                .password("prinky234")
                .build();
        userService.createUser(userRequest2);


        User user =userService.getAUserById(1L);
        assertEquals(user.getEmail(),"lasers@gmail.com");
        assertEquals(user.getId(),1);
    }


}