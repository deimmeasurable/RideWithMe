package com.example.ridewithme.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionAgent {
    private String  driverEmail;
    private int commissionAmount;
}
