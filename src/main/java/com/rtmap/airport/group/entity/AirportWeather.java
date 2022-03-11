package com.rtmap.airport.group.entity;


import lombok.Data;

@Data
public class AirportWeather {

    private String airportCode;

    private String ariportNameAbbr;

    private String pm;

    private String temperature;

    private String weather;
}
