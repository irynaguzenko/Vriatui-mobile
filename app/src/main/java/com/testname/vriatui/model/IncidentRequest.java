package com.testname.vriatui.model;

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
    public class Coordinates {
        double x;
        double y;
    }
}
