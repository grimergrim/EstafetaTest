package com.avtologistika.test.screens.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.R;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.screens.main.adapters.TaskAdapter;
import com.avtologistika.test.screens.task.TaskActivity;
import com.avtologistika.test.utils.InMemoryCache;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Inject
    protected InMemoryCache mInMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalApplication) this.getApplication()).getMainComponent().inject(this);

        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tasks_recycler_view);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        if (mInMemoryCache.getTaskList().size() > 0) {
            TaskAdapter taskAdapter = new TaskAdapter(mInMemoryCache.getTaskList(),
                    getApplicationContext(), this);
            recyclerView.setAdapter(taskAdapter);
        }
    }

    @Override
    public void openTask(int id) {
        Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
        intent.putExtra(Constants.EXTRA_TASK_ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
}
