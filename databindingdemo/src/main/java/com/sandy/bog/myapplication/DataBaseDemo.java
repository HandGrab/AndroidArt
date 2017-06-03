package com.sandy.bog.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandy Luo on 2017/6/1.
 */

public class DataBaseDemo {

    public static List<School> getSchoolList(){
        List<School> data = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            School school = new School();
            school.setName("number:" + i);

            school.setStudent(new Student(String.valueOf(100 + i)));
            data.add(school);
        }
        return data;
    }
}
