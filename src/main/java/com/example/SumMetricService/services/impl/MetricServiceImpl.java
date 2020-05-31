package com.example.SumMetricService.services.impl;

import com.example.SumMetricService.exception.MetricException;
import com.example.SumMetricService.models.Visitor;
import com.example.SumMetricService.models.requests.MetricRequest;
import com.example.SumMetricService.models.responses.MetricResponse;
import com.example.SumMetricService.services.MetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class MetricServiceImpl implements MetricService {

    private static final Map<String, ArrayList<Visitor>> map = new HashMap<>();

    /**
     *  Adding value to a key's array
     * @param key :String
     * @param metricRequest :MetricRequest
     */
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


    /**
     *
     * @param key: String
     * @return : MetricResponse
     * @throws MetricException
     */
    @Override
    public MetricResponse getMetric(String key) throws MetricException {
        if(!map.containsKey(key)) throw new MetricException("Missing key");
        MetricResponse response = new MetricResponse();
        ArrayList<Visitor> listForKey = map.get(key);
        response.setValue(listForKey.stream().filter(visitor -> TimeUnit.HOURS.convert(new Date().getTime() - visitor.getTimeRecorded().getTime(), TimeUnit.MILLISECONDS) <= 1).mapToInt(Visitor::getValue).sum());
        return response;

    }

    private Visitor createVisitorFromRequest(MetricRequest metricRequest) {
        Visitor visitor = new Visitor();
        visitor.setValue(metricRequest.getValue());
        visitor.setTimeRecorded(new Date());
        return visitor;
    }


}
