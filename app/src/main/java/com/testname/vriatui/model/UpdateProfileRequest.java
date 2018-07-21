package com.testname.vriatui.model;

import java.util.Set;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    String firstName;
    String lastName;
    Integer yearOfBirth;
    Gender gender;
    String diseases;
    Set<String> phonesToNotify;
    Address address;

    enum Gender {
        MALE, FEEMALE
    }
}
