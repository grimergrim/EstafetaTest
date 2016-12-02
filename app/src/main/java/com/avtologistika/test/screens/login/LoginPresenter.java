package com.avtologistika.test.screens.login;

import com.avtologistika.test.ServiceGenerator;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.api.HttpEndpointsApi;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.utils.InMemoryCache;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView mLoginView;
    private InMemoryCache mInMemoryCache;

    @Inject
    public LoginPresenter(InMemoryCache inMemoryCache) {
        mInMemoryCache = inMemoryCache;
    }

    @Override
    public void setVIew(LoginContract.LoginView view) {
        mLoginView = view;
    }

    @Override
    public void getTasksAndLogin(String login, String password) {
        HttpEndpointsApi loginService =
                ServiceGenerator.createService(HttpEndpointsApi.class,
                        login + Constants.DEFAULT_COMPANY_ID, password);

        Call<List<Task>> call = loginService.getTasksAndLogin();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && null != response.body()) {
                    mInMemoryCache.setTaskList(response.body());
                    if (null != mLoginView) {
                        mLoginView.goToTaskList();
                    }
                } else {
                    if (null != mLoginView) {
                        mLoginView.showError();
                        mLoginView.showProgress(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                if (null != mLoginView) {
                    mLoginView.showError();
                    mLoginView.showProgress(false);
                }
            }
        });

    }

}
