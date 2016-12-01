package com.avtologistika.test.di;

import com.avtologistika.test.screens.login.LoginActivity;
import com.avtologistika.test.screens.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);

}