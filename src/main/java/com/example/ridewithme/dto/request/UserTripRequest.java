package com.example.ridewithme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTripRequest {
    private String  email;
    private String  location;
    private String  Destination;
    private boolean tripStatus;
}
