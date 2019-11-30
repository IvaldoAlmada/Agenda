package com.ivaldoalmada.dao;

import com.ivaldoalmada.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static int alunoIdIncrement = 1;

    private static final List<Student> studentList = new ArrayList<>();

    public void save(Student student) {
        student.setId(alunoIdIncrement);
        studentList.add(student);
        alunoIdIncrement++;
    }

    public void edit(Student student) {
        Student studentFound = null;
        for (Student s: studentList) {
            if(s.getId() == student.getId()) {
                studentFound = s;
            }
        }
        if(studentFound != null) {
            int studentfoundPosition = studentList.indexOf(studentFound);
            studentList.set(studentfoundPosition, student);
        }
    }

    public List<Student> findAll() {
        return studentList;
    }
}
