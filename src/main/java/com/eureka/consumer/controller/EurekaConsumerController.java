package com.eureka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class EurekaConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/consumer")
    public String consumer(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-producer");
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/producer";
        System.out.println(url);
        return  restTemplate.getForObject(url,String.class);
    }
}
