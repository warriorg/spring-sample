package me.warriorg.dubbo.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author warrior
 */
public interface OtherService {

    String doFirst();
    String doSecond();
    String doThird();
    String doFourth();

    CompletableFuture<String> doFifth();
    CompletableFuture<String> doSixth();
}
