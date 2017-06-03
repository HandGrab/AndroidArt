package com.sandy.bog.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Sandy Luo on 2017/6/1.
 */

public class School extends BaseObservable{

    private String name;

    private Student student = new Student("hello");


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
