package com.avtologistika.test.screens.login;

import com.avtologistika.test.screens.main.MainContract;

public interface LoginContract {

    interface LoginView {

    }

    interface LoginPresenter {

        void setVIew(MainContract.MainView view);

        void basicLogin(String login, String password);

    }

}
