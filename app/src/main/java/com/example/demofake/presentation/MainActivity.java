package com.example.demofake;

import android.os.Bundle;
import android.util.Log;

import com.example.demofake.data.repository.remote.Service;
import com.example.demofake.data.repository.remote.request.GetRequest;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCivilizations();
    }

    public void getCivilizations(){

        GetRequest getRequest = Service.getInstance(GetRequest.class);
        Call<Integer> objectCall = getRequest.sumNumbers(20, 30);
        objectCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int statusCode = response.code();
                Log.d("STATUS", String.valueOf(statusCode));
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }
}
