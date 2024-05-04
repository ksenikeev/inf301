package ru.itis.inf301.lab2_5.transport.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Geometry {
    String type;
    List<Double[]> coordinates;
}
