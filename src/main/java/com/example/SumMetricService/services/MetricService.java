package com.example.SumMetricService.services;

import com.example.SumMetricService.models.requests.MetricRequest;

public interface MetricService {

    void postMetric(String key, MetricRequest metricRequest);

}
