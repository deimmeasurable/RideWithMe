package com.example.ridewithme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTripResponse {
    private String  email;
    private String  location;
    private String  destination;
    private boolean tripStatus;

}
