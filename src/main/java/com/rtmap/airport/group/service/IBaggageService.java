package com.rtmap.airport.group.service;


import com.rtmap.airport.group.result.CommonResponse;

public interface IBaggageService {

    CommonResponse queryBaggage(String bagNo);
}
