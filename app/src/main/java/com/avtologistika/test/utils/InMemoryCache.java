package com.avtologistika.test.utils;

import com.avtologistika.test.entities.Task;

import java.util.List;

public class InMemoryCache {

    private List<Task> mTaskList;

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }
}
