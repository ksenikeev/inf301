package ru.itis.inf301.lab2_5.transport.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter@Getter
public class Feature {
    private Geometry geometry;
    private FeatureProperties properties;
}
