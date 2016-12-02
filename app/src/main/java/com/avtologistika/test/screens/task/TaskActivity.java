package com.avtologistika.test.screens.task;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.R;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.utils.InMemoryCache;

import javax.inject.Inject;

public class TaskActivity extends AppCompatActivity implements TaskContract.TaskView {

    private int currentTaskIndex;
    private TextView mIdView;
    private TextView mNumberView;
    private TextView mCarrierView;
    private TextView mDriverView;
    private TextView mVinView;
    private TextView mModelView;
    private TextView mModelCodeView;
    private TextView mbrandView;
    private TextView mPlannedStartDateView;
    private TextView mPlannedEndDateView;
    private TextView mActualStartDateView;
    private TextView mActualEndDateView;
    private TextView mSurveyPointView;
    private ActionBar mToolbar;

    @Inject
    protected InMemoryCache mInMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalApplication) this.getApplication()).getMainComponent().inject(this);



        setContentView(R.layout.activity_task);
        currentTaskIndex = getIntent().getIntExtra(Constants.EXTRA_TASK_ID, 0);
        findViews();
        setTextToUi();

        if (null != mToolbar) {
            mToolbar.setTitle(R.string.toolbat_title);
            mToolbar.setSubtitle(R.string.toolbat_subtitle);
        }
    }

    private void findViews() {
        mIdView = (TextView) findViewById(R.id.id);
        mNumberView = (TextView) findViewById(R.id.number);
        mCarrierView = (TextView) findViewById(R.id.carrier);
        mDriverView = (TextView) findViewById(R.id.driver);
        mVinView = (TextView) findViewById(R.id.vin);
        mModelView = (TextView) findViewById(R.id.model);
        mModelCodeView = (TextView) findViewById(R.id.model_code);
        mbrandView = (TextView) findViewById(R.id.brand);
        mPlannedStartDateView = (TextView) findViewById(R.id.planned_start_date);
        mPlannedEndDateView = (TextView) findViewById(R.id.planned_end_date);
        mActualStartDateView = (TextView) findViewById(R.id.actual_start_date);
        mActualEndDateView = (TextView) findViewById(R.id.actual_end_date);
        mSurveyPointView = (TextView) findViewById(R.id.survey_point);
        mToolbar = getSupportActionBar();

    }

    private void setTextToUi() {
        mIdView.setText(String.valueOf(mInMemoryCache.getTaskList().get(currentTaskIndex).getId()));
        mNumberView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getNumber());
        mCarrierView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getCarrier());
        mDriverView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getDriver());
        mVinView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getVin());
        mModelView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getModel());
        mModelCodeView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getModelCode());
        mbrandView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getBrand());
        mPlannedStartDateView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getPlannedStartDate());
        mPlannedEndDateView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getPlannedEndDate());
        mActualStartDateView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getActualStartDate());
        mActualEndDateView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getActualEndDate());
        mSurveyPointView.setText(mInMemoryCache.getTaskList().get(currentTaskIndex).getSurveyPoint());
    }

}
