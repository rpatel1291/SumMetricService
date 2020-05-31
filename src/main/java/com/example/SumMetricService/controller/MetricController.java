package com.example.SumMetricService.controller;

import com.example.SumMetricService.exception.MetricException;
import com.example.SumMetricService.models.requests.MetricRequest;
import com.example.SumMetricService.models.responses.MetricResponse;
import com.example.SumMetricService.services.MetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController(value = "/metric")
@Log4j2
public class MetricController {

    MetricService metricService;

    @Autowired
    MetricController(MetricService metricService){
        this.metricService = metricService;
    }


    @PostMapping(value = "/{key}")
    public ResponseEntity postMetric(@PathVariable(value = "key") String key,
                                     @RequestBody MetricRequest metricRequest) throws  MetricException{

        metricService.postMetric(Optional.ofNullable(key).orElseThrow(MetricException::new), Optional.ofNullable(metricRequest).orElseThrow(MetricException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{key}/sum")
    public ResponseEntity<MetricResponse> getMetric(@PathVariable(value = "key") String key) throws MetricException{
        log.info("key: {}", key);
        return new ResponseEntity<>(metricService.getMetric(Optional.ofNullable(key).orElseThrow(MetricException::new)), HttpStatus.OK);
    }

}
