package com.rtmap.airport.group.vo;

import com.rtmap.airport.group.entity.AirportWeather;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: gaowm
 * @date: 2022/3/1
 * @desc:
 */
@Getter
@Setter
public class FltVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sourceId;
    private String ffid;
    private String iata;
    private String airlinName;
    private String fltNo;
    private String fltDate;
    private String aord;
    private String task;
    private String domint;
    private String shareFltNo;
    private String regNumber;
    private String airType;
    private String depAirportCode;
    private String depAirportNameCn;
    private String depAirportNameEn;
    private String depSdt;
    private String depEst;
    private String depAct;
    private String depTerminal;
    private AirportWeather depAirportWeather;
    private String arrAirportCode;
    private String arrAirportNameCn;
    private String arrAirportNameEn;
    private String arrSdt;
    private String arrEst;
    private String arrAct;
    private String arrTerminal;
    private String arrPark;
    private AirportWeather arrAirportWeather;
    private String fltStatus;
    private String cnlReason;
    private String route;
    private String routeName;
    private String counter;
    private String gate;
    private String belt;
    private String boardingTime;
    private String urageBoardingTime;
    private String endBoardingTime;
    private String firstBaggageTime;
    private String lastBaggageTime;
    /**进港上一站实际起飞时间*/
    private String preActTime;
    /**出港下一站实际落地时间*/
    private String nextActTime;

    private Integer subScribeId;
    private String passRoute;



}
