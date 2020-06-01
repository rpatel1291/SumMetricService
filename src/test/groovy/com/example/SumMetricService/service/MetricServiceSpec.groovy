package com.example.SumMetricService.service

import com.example.SumMetricService.models.Visitor
import com.example.SumMetricService.models.requests.MetricRequest
import com.example.SumMetricService.services.MetricService
import com.example.SumMetricService.services.impl.MetricServiceImpl
import spock.lang.Specification

class MetricServiceSpec extends Specification{

    MetricService metricService


    def setup(){
        metricService = new MetricServiceImpl()
    }

    def "get Metric"(){
        given:
            Visitor v1 = createVisitor(10)
            Visitor v2 = createVisitor(20)
            Visitor v3 = createVisitor(30)
            ArrayList<Visitor> list = new ArrayList<>();
            list.add(v1)
            list.add(v2)
            list.add(v3)
            HashMap<String, ArrayList<Visitor>> map = new HashMap<>()
            map.put("key", list)
        when:
            def response = metricService.getMetric(key)
        then:
            response.value == result
        where:
            key     | result
            "key"   | 60
    }

    def "post Metric"(){
        given:
            ArrayList<Visitor> listForKey = new ArrayList<>()
        when:
            metricService.postMetric(key, metricRequest)
        then:
            listForKey != null
        where:
        key     |       metricRequest
        "key"   |       createMetricRequest()

    }

    MetricRequest createMetricRequest(){
        MetricRequest metricRequest = new MetricRequest()
        metricRequest.value = 10
        metricRequest
    }

    Visitor createVisitor(int value){
        Visitor visitor = new Visitor()
        visitor.value = value
        visitor.timeRecorded = new Date()
        visitor
    }



}
