package com.layer_arch.userdemo.controller;


import com.layer_arch.userdemo.service.impl.ParallelService;
import com.layer_arch.userdemo.service.impl.ParallelServiceFeign;
import com.layer_arch.userdemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/parallel")
public class ParallelController {

//    @Autowired
//    private ParallelService service;

    @Autowired
    private ParallelServiceFeign service;

//    @GetMapping
//    public ResponseEntity<String[]> getParallel() throws ExecutionException, InterruptedException {
//        return ResponseEntity.ok().body(service.executeEndPointsParallel().get());
//    }

    @GetMapping
    public ResponseEntity<String[]> getParallel(){
        return ResponseEntity.ok().body(service.executeEndPointsParallel().join());
    }

}
