package com.ivaldoalmada.dao;

import com.ivaldoalmada.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static final List<Student> studentList = new ArrayList<>();

    public void save(Student student) {
        studentList.add(student);
    }

    public List<Student> findAll() {
        return studentList;
    }
}
