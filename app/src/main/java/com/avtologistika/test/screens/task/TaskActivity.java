package com.avtologistika.test.screens.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.R;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.utils.InMemoryCache;

import javax.inject.Inject;

public class TaskActivity extends AppCompatActivity implements TaskContract.TaskView {

    @Inject
    protected InMemoryCache mInMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalApplication) this.getApplication()).getMainComponent().inject(this);
        setContentView(R.layout.activity_task);
        int currentTaskIndex = getIntent().getIntExtra(Constants.EXTRA_TASK_ID, 0);
        Task currentTask = mInMemoryCache.getTaskList().get(currentTaskIndex);
    }
}
