package com.avtologistika.test.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.avtologistika.test.R;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
