package com.sandy.bog.myapplication;

import android.databinding.Observable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sandy Luo on 2017/6/1.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<School> mData;

    private Handler uiHandler;

    public MyAdapter(List<School> mData) {
        this.mData = mData;
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        School oldSchool = holder.getSchool();
        if (oldSchool != null) {
            oldSchool.getStudent().removeOnPropertyChangedCallback(holder.getCallback());
        }

        School school = mData.get(position);
        holder.mName.setText(school.getName());
        holder.mCount.setText(school.getStudent().getCount());
        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, final int i) {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (i) {
                            case BR.count:
                                holder.mCount.setText(school.getStudent().getCount());
                                break;

                            case BR.name:
                                holder.mName.setText(school.getName());
                                break;

                        }
                    }
                });
            }
        };


        school.addOnPropertyChangedCallback(callback);
        school.getStudent().addOnPropertyChangedCallback(callback);
        holder.setSchool(school);
        holder.setCallback(callback);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private Observable.OnPropertyChangedCallback callback;

        private TextView mName;

        private TextView mCount;

        private School school;

        public MyHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.school_name);
            mCount = (TextView) itemView.findViewById(R.id.student_count);
        }

        public void setSchool(School school) {
            this.school = school;
        }

        public School getSchool() {
            return school;
        }

        public Observable.OnPropertyChangedCallback getCallback() {
            return callback;
        }

        public void setCallback(Observable.OnPropertyChangedCallback callback) {
            this.callback = callback;
        }
    }
}
