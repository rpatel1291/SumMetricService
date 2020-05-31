package com.example.SumMetricService.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MetricException extends  Exception{

    public MetricException(){}

    public MetricException(String message){
        log.error(message);
    }

}
