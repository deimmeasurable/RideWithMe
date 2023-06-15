package com.example.ridewithme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionAgentRequest {
    private String  driverEmail;
    private double commissionAmount; private String userEmail;
 }
