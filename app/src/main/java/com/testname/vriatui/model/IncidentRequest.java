package com.testname.vriatui.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Value;

@Data
public class IncidentRequest {
    String profileId;
    Location location;
    Address happenInAddress;
    boolean happenAtHome;
    String problem;

    @Value
    public static class Location implements Serializable {
        String type = "Point";
        double[] coordinates;

        public Location(double x, double y) {
            coordinates = new double[]{x, y};
        }
    }
}
