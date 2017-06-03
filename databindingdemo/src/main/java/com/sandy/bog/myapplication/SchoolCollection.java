package com.sandy.bog.myapplication;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import java.util.List;

/**
 * Created by Sandy Luo on 2017/6/1.
 */

public class SchoolCollection {

    private static SchoolCollection instance;

    public static SchoolCollection getInstance() {
        if (instance == null) {
            instance = new SchoolCollection();
        }
        return instance;
    }

    public SchoolCollection() {
    }

    private ObservableList<School> schools = new ObservableArrayList<School>();

    public void refreshData() {
        List<School> schoolList = DataBaseDemo.getSchoolList();
        if (schools.size() > 0) {
            schools.clear();
        }
        schools.addAll(schoolList);
    }

    public List<School> getSchools() {
        return schools;
    }

    public void onDataChange() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (School school : schools) {
                    synchronized (schools) {
                        try {
                            Thread.sleep(1000);
                            school.setName("Hello World");
                            school.getStudent().setCount("what the fuck?");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.d("SchoolCollection", e.toString());
                        }

                    }
                }
            }
        }).start();
    }

    public void addData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (schools) {
                        try {
                            Thread.sleep(1000);
                            School school = new School();
                            school.setName("SB:" + i);
                            schools.add(school);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.d("SchoolCollection", e.toString());
                        }

                    }
                }
            }
        }).start();
    }


    public void addSchoolListCallback(ObservableList.OnListChangedCallback
                                              <ObservableList<School>> callback) {
        schools.addOnListChangedCallback(callback);
    }

    public void removeSchoolListCallback(ObservableList.OnListChangedCallback
                                                 <ObservableList<School>> callback) {
        schools.removeOnListChangedCallback(callback);
    }
}
