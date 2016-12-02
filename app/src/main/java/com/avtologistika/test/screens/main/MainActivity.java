package com.avtologistika.test.screens.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.R;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.screens.main.adapters.TaskAdapter;
import com.avtologistika.test.screens.search.SearchResultsActivity;
import com.avtologistika.test.screens.task.TaskActivity;
import com.avtologistika.test.utils.InMemoryCache;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ActionBar mToolbar;

    @Inject
    protected InMemoryCache mInMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalApplication) this.getApplication()).getMainComponent().inject(this);
        setContentView(R.layout.activity_main);
        findViews();

        if (null != mToolbar) {
            mToolbar.setTitle(R.string.toolbar_title);
            mToolbar.setSubtitle(R.string.toolbar_subtitle);
        }

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
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                List<Task> searchResults = mInMemoryCache.search(query);
                if (null != searchResults && searchResults.size() > 0) {
                    mInMemoryCache.setSearchQuery(query);
                    Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().
                            getString(R.string.no_results), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        searchView.setIconifiedByDefault(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fade_in_from_left, R.anim.fade_out_to_right);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        overridePendingTransition(R.anim.fade_in_from_left, R.anim.fade_out_to_right);
    }

    private void findViews() {
        mToolbar = getSupportActionBar();
    }

}
