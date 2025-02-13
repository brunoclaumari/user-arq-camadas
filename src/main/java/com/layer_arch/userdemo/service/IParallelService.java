package com.layer_arch.userdemo.service;

import java.util.concurrent.CompletableFuture;

public interface IParallelService {

    public CompletableFuture<String> callApi(String endPoint);

    public CompletableFuture<String[]> executeEndPointsParallel();

}
