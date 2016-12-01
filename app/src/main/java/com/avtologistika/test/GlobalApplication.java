package com.avtologistika.test;

import android.app.Application;
import android.content.Context;

import com.avtologistika.test.api.Constants;
import com.avtologistika.test.di.DaggerMainComponent;
import com.avtologistika.test.di.MainComponent;
import com.avtologistika.test.di.MainModule;

public class GlobalApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger2 config
        mainComponent = DaggerMainComponent
                .builder()
                .mainModule(new MainModule(Constants.BASE_URL, this))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
