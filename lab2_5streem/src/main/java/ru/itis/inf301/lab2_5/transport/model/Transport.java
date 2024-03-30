package ru.itis.inf301.lab2_5.transport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Transport {
    private String id;
    private String threadId;
    private String lineId;
    private String name;
    @JsonProperty("Types")
    private String[] sypes;
    private String type;
    private String uri;
    private String seoname;
}