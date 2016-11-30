package com.avtologistika.test.di;

import com.avtologistika.test.screens.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(LoginActivity loginActivity);

}