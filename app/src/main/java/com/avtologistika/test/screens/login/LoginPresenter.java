package com.avtologistika.test.screens.login;

import android.util.Log;

import com.avtologistika.test.ServiceGenerator;
import com.avtologistika.test.api.HttpEndpointsApi;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.screens.main.MainContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private MainContract.MainView mMainView;

    @Override
    public void setVIew(MainContract.MainView view) {
        mMainView = view;
    }

    @Override
    public void basicLogin(String login, String password) {

        HttpEndpointsApi loginService =
                ServiceGenerator.createService(HttpEndpointsApi.class,
                        "estafeta@9F346DDB-8FF8-4F42-8221-6E03D6491756", "1");
        Call<List<Task>> call = loginService.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    // user object available
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

    }

}
