package com.avtologistika.test.screens.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avtologistika.test.R;
import com.avtologistika.test.entities.Task;
import com.avtologistika.test.screens.main.MainContract;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

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
        setListener(taskViewHolder, position);
        taskViewHolder.mCarrierNameView.setText(mTaskList.get(position).getCarrier());
        taskViewHolder.mDriverNameView.setText(mTaskList.get(position).getDriver());
        taskViewHolder.mNumberView.setText(mTaskList.get(position).getNumber());
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
        return new TaskViewHolder(itemView);
    }

    private void setListener(final TaskViewHolder taskViewHolder, final int position) {
        taskViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainView.openTask(position);
            }
        });
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout container;
        private ImageView mCarrierLogoView;
        private TextView mCarrierNameView;
        private TextView mDriverNameView;
        private TextView mNumberView;

        TaskViewHolder(View v) {
            super(v);
            container = (RelativeLayout) v.findViewById(R.id.task_container);
            mCarrierLogoView = (ImageView) v.findViewById(R.id.carrier_logo);
            mCarrierNameView = (TextView) v.findViewById(R.id.carrier_name);
            mDriverNameView = (TextView) v.findViewById(R.id.driver_name);
            mNumberView = (TextView) v.findViewById(R.id.number);
        }

        void clearAnimation() {
            if (container != null) {
                container.clearAnimation();
            }
        }
    }

}