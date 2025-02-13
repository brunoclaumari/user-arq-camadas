package com.layer_arch.userdemo.service.impl;

import com.layer_arch.userdemo.service.IParallelService;
import com.layer_arch.userdemo.service.ParallelFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ParallelServiceFeign implements IParallelService {

    @Autowired
    private ParallelFeignClient parallelFeignClient;


    @Async
    @Override
    public CompletableFuture<String> callApi(String endPoint) {
        String url = endPoint;
        //return CompletableFuture.completedFuture(restTemplate.getForObject(url, String.class));
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Thread: %s",Thread.currentThread().getName()));
            //return restTemplate.getForObject(url, String.class);
            return getSelectedApi(endPoint);
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

    private String getSelectedApi(String endPoint){
        String resultApi = "";
        switch (endPoint) {
            case "api2":
                resultApi = parallelFeignClient.api2();
                break;
            case "api3":
                resultApi = parallelFeignClient.api3();
                break;
            default:
                resultApi = parallelFeignClient.api1();
                break;
        }

        return resultApi;
    }

}
