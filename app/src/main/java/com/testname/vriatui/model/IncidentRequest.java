package com.testname.vriatui.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Value;

@Data
public class IncidentRequest {
    String profileId;
    Coordinates location;
    Address happenInAddress;
    boolean happenAtHome;
    String problem;

    @Value
    public static class Coordinates implements Serializable {
        double x;
        double y;
    }
}
