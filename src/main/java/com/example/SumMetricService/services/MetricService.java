package com.example.SumMetricService.services;

import com.example.SumMetricService.exception.MetricException;
import com.example.SumMetricService.models.requests.MetricRequest;
import com.example.SumMetricService.models.responses.MetricResponse;

public interface MetricService {

    void postMetric(String key, MetricRequest metricRequest);

    MetricResponse getMetric(String key) throws MetricException;

}
