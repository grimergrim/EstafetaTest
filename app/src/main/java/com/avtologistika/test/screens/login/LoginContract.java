package com.avtologistika.test.screens.login;

public interface LoginContract {

    interface LoginView {

        void goToTaskList();

        void showError();

        void showProgress(boolean show);

    }

    interface LoginPresenter {

        void setVIew(LoginView view);

        void getTasksAndLogin(String login, String password);

    }

}
