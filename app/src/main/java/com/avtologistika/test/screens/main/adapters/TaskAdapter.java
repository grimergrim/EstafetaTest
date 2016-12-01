package com.avtologistika.test.screens.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.avtologistika.test.R;
import com.avtologistika.test.entities.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>
        implements View.OnClickListener {

    private List<Task> mTaskList;
    private Context mContext;

    public TaskAdapter(List<Task> taskList, Context context) {
        this.mTaskList = taskList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
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
        //TODo implement listener
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;

        TaskViewHolder(View v) {
            super(v);
            container = (RelativeLayout) v.findViewById(R.id.task_container);
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