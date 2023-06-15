package com.example.ridewithme.dto.request;

import com.example.ridewithme.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckUserLocation {
    private String  userEmail;
    private String  location;
    private String  driverEmail;

}
