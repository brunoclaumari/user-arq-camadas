package com.layer_arch.userdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "consumer-api",url = "http://localhost:8082/")
public interface ParallelFeignClient {

    @GetMapping(value = "/api1")
    public String api1();

    @GetMapping(value = "/api2")
    public String api2();

    @GetMapping(value = "/api3")
    public String api3();
}
