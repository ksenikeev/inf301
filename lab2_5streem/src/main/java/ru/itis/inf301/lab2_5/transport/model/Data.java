package ru.itis.inf301.lab2_5.transport.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter@Getter
public class Data {
    private List<Vehicle> vehicles;
    private RegionInfo regionInfo;
}
