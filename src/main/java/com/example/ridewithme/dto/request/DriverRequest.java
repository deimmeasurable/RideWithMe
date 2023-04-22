package com.example.ridewithme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverRequest {
    private String  userName;
    private String  phoneNumber;
    private String  email;
    private String  firstName;
    private String  lastName;
    private String  password;
    private Long    id;
}
