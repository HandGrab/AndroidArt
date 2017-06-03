package com.sandy.bog.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Sandy Luo on 2017/6/1.
 */

public class Student extends BaseObservable {

    private String count = "";

    public Student(String count) {
        this.count = count;
    }

    public void setCount(String count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }

    @Bindable
    public String getCount() {
        return count;
    }
}
