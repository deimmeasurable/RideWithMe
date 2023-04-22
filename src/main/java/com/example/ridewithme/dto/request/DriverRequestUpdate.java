package com.example.ridewithme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverRequestUpdate {
    private String  userName;
    private String  phoneNumber;
    private String  email;
    private String  firstName;
    private String  lastName;
    private String  password;
}
