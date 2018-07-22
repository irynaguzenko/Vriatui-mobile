package com.testname.vriatui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.Data;

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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location implements Serializable {
        String type;
        double x;
        double y;
    }
}
