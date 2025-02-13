package com.layer_arch.userdemo.service.impl;

import com.layer_arch.userdemo.service.IParallelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class ParallelService implements IParallelService {

    @Autowired
    private RestTemplate restTemplate;

    private final String host = "http://localhost:8082/";

    @Async
    @Override
    public CompletableFuture<String> callApi(String endPoint) {
        String url = host + endPoint;
        //return CompletableFuture.completedFuture(restTemplate.getForObject(url, String.class));
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Thread: %s",Thread.currentThread().getName()));
            return restTemplate.getForObject(url, String.class);
        });
    }

    //Jeito que deu certo.
    @Override
    public CompletableFuture<String[]> executeEndPointsParallel() {

        System.out.println("*********Chamadas da API*************");
        CompletableFuture<String> future1 = callApi("api1");
        CompletableFuture<String> future2 = callApi("api2");
        CompletableFuture<String> future3 = callApi("api3");
        CompletableFuture<String> future4 = callApi("api1");

        CompletableFuture<String> future5 = callApi("api3");
        CompletableFuture<String> future6 = callApi("api2");


        return CompletableFuture.allOf()
                .thenApply(v -> new String[]{
                        future1.join(),
                                future2.join(),
                                future3.join(),
                                future4.join(),
                                future5.join(),
                                future6.join(),
                }
                );
    }

    //Um outro jeito de fazer
    public CompletableFuture<String[]> executeEndPointsParallel2(){
        String[] vetor = new String[2];

        CompletableFuture.allOf(
                        callApi("api1").thenAccept(t1->vetor[0]=t1),
                        callApi("api2").thenAccept(t2->vetor[1]=t2)
                ).join();

        return CompletableFuture.completedFuture(vetor);
    }

}
