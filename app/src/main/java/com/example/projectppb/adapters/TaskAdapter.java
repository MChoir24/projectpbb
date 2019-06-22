package com.example.projectppb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectppb.R;
import com.example.projectppb.activities.ViewTask;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private ArrayList<Task> mDataset;
    private Context mContext;
    DataHelper dbcenter;

    public TaskAdapter(Context mContext, ArrayList<Task> mDataset) {
        this.mDataset = mDataset;
        this.mContext = mContext;
        dbcenter = new DataHelper(mContext);
    }

    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View)  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_task, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder viewHolder, final int i) {
        viewHolder.tv_nama.setText(mDataset.get(i).getNama());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewTask.class);
                intent.putExtra(ViewTask.EXTRA_PERSON, mDataset.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mDataset != null) ? mDataset.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama;
        private CardView cardView;
        final TaskAdapter taskAdapter ;

        public MyViewHolder(@NonNull View itemView, TaskAdapter taskAdapter) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.namaTask);
            cardView = itemView.findViewById(R.id.task_layout);
            this.taskAdapter = taskAdapter;
        }
    }
}
