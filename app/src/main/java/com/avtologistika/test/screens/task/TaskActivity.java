package com.avtologistika.test.screens.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.avtologistika.test.R;

public class TaskActivity extends AppCompatActivity implements TaskContract.TaskView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
}
