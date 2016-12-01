package com.avtologistika.test.screens.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avtologistika.test.R;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.screens.main.MainContract;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>
        implements View.OnClickListener {

    private List<Task> mTaskList;
    private Context mContext;
    private MainContract.MainView mMainView;

    public TaskAdapter(List<Task> taskList, Context context, MainContract.MainView mainView) {
        this.mTaskList = taskList;
        this.mContext = context;
        this.mMainView = mainView;
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
        taskViewHolder.mCarrierNameView.setText(mTaskList.get(position).getCarrier());
        taskViewHolder.mDriverNameView.setText(mTaskList.get(position).getDriver());
        if (position % 2 == 0) {
            slideInAnimation(taskViewHolder.container, true);
        } else {
            slideInAnimation(taskViewHolder.container, false);
        }
    }

    @Override
    public void onViewDetachedFromWindow(TaskViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_list_item, viewGroup, false);
        itemView.setOnClickListener(this);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        //TODO pass real task id to method below
        mMainView.openTask(1);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        ImageView mCarrierLogoView;
        TextView mCarrierNameView;
        TextView mDriverNameView;

        TaskViewHolder(View v) {
            super(v);
            container = (RelativeLayout) v.findViewById(R.id.task_container);
            mCarrierLogoView = (ImageView) v.findViewById(R.id.carrier_logo);
            mCarrierNameView = (TextView) v.findViewById(R.id.carrier_name);
            mDriverNameView = (TextView) v.findViewById(R.id.driver_name);
        }

        void clearAnimation() {
            if (container != null) {
                container.clearAnimation();
            }
        }
    }

    private void slideInAnimation(View viewToAnimate, boolean isFromLeft) {
        Animation animation;
        if (isFromLeft) {
            animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left);
        } else {
            animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right);
        }
        viewToAnimate.startAnimation(animation);
    }


}