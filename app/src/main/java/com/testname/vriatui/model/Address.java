package com.testname.vriatui.model;

import lombok.Data;

@Data
public class Address {
    String city;
    //street + house + flat etc.
    String value;
    Integer entrance;
    Integer floor;
    String comment;
}
