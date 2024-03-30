package ru.itis.inf301.lab2_5.transport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class VehicleMetaData {
    private String id;
    @JsonProperty("Transport")
    private Transport transport;
}
