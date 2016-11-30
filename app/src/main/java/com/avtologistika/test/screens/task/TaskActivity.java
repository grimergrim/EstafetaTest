package com.avtologistika.test.screens.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.avtologistika.test.R;
import com.avtologistika.test.screens.main.MainContract;

public class TaskActivity extends AppCompatActivity implements MainContract.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
}
