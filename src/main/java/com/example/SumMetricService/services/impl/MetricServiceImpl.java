package com.example.SumMetricService.services.impl;

import com.example.SumMetricService.models.Visitor;
import com.example.SumMetricService.models.requests.MetricRequest;
import com.example.SumMetricService.services.MetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class MetricServiceImpl implements MetricService {

    private static final Map<String, ArrayList<Visitor>> map = new HashMap<>();

    @Override
    public void postMetric(String key, MetricRequest metricRequest) {

        Visitor visitor = createVisitorFromRequest(Optional.ofNullable(metricRequest).orElse(new MetricRequest()));
        ArrayList<Visitor> listForKey;
        if (map.containsKey(key)){
            listForKey = map.get(key);
        }
        else{
            listForKey = new ArrayList<>();
        }
        listForKey.add(visitor);
        map.put(key, listForKey);
        log.info("Map : {}", map);
        log.info("Visitor : {}", visitor);
    }

    private Visitor createVisitorFromRequest(MetricRequest metricRequest) {
        Visitor visitor = new Visitor();
        visitor.setValue(metricRequest.getValue());
        visitor.setTimeRecorded(new Date());
        return visitor;
    }


}
