package com.avtologistika.test.utils;

import com.avtologistika.test.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCache {

    private List<Task> mTaskList;

    private List<Task> mSearchResultTaskList;

    private String searchQuery;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public List<Task> getSearchResultTaskList() {
        return mSearchResultTaskList;
    }

    public void setSearchResultTaskList(List<Task> searchResultTaskList) {
        mSearchResultTaskList = searchResultTaskList;
    }

    public List<Task> search(String query) {
        List<Task> results = new ArrayList<>();
        for (Task task : mTaskList) {
            if (null != task.getNumber()
                    && task.getNumber().toLowerCase().contains(query.toLowerCase())
                    || null != task.getPlannedStartDate()
                    && task.getPlannedStartDate().toLowerCase().contains(query.toLowerCase())
                    || null != task.getPlannedEndDate()
                    && task.getPlannedEndDate().toLowerCase().contains(query.toLowerCase())
                    || null != task.getActualStartDate()
                    && task.getActualStartDate().toLowerCase().contains(query.toLowerCase())
                    || null != task.getActualEndDate()
                    && task.getActualEndDate().toLowerCase().contains(query.toLowerCase())
                    || null != task.getVin()
                    && task.getVin().toLowerCase().contains(query.toLowerCase())
                    || null != task.getModel()
                    && task.getModel().toLowerCase().contains(query.toLowerCase())
                    || null != task.getModelCode()
                    && task.getModelCode().toLowerCase().contains(query.toLowerCase())
                    || null != task.getBrand()
                    && task.getBrand().toLowerCase().contains(query.toLowerCase())
                    || null != task.getSurveyPoint()
                    && task.getSurveyPoint().toLowerCase().contains(query.toLowerCase())
                    || null != task.getCarrier()
                    && task.getCarrier().toLowerCase().contains(query.toLowerCase())
                    || null != task.getDriver()
                    && task.getDriver().toLowerCase().contains(query.toLowerCase())
                    ) {
                results.add(task);
            }
        }
        mSearchResultTaskList = results;
        return results;
    }
}
