package com.example.demofake.data.repository.remote.request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRequest {

    // NORMAL
    @GET("suma/{firstNumber}/{secondNumber}")
    Call<Integer> sumNumbers(@Path("firstNumber") int firstNumber,
                             @Path("secondNumber") int secondNumber);

    @GET("resta/{firstNumber}/{secondNumber}")
    Call<Integer> sustractNumbers(@Path("firstNumber") int firstNumber,
                                  @Path("secondNumber") int secondNumber);

    @GET("div/{firstNumber}/{secondNumber}")
    Call<Integer> divideNumbers(@Path("firstNumber") int firstNumber,
                                @Path("secondNumber") int secondNumber);

    @GET("multi/{firstNumber}/{secondNumber}")
    Call<Integer> multiplyNumbers(@Path("firstNumber") int firstNumber,
                                  @Path("secondNumber") int secondNumber);

    // RX
}
