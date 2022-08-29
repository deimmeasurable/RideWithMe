package com.example.ridewithme.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable=false)
    private Long id;
    private String firstName;
    private String lastName;


    private String email;


    private String password;

}
