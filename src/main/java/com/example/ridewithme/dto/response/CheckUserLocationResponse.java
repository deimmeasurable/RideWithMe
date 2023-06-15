package com.example.ridewithme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
 public class CheckUserLocationResponse {
    private String  userEmail;
    private String  driverEmail;
    private String  location;

    private String  message;

}
