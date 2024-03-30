package ru.itis.inf301.lab2_5.transport.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class Vehicle {
    private String type;
    private Properties properties;
    private List<Feature> features;
}
