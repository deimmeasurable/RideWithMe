package com.example.ridewithme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionAgentResponse {
    private String  email;
    private double  commissionAmount;
}
