package com.testname.vriatui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Address {
    String city;
    //street + house + flat etc.
    String value;
    Integer entrance;
    Integer floor;
    String comment;
    Location location;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location implements Serializable {
        String type;
        double x;
        double y;
    }
}
