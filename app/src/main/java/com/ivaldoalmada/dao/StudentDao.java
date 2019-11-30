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
        Student studentFound = findById(student.getId());
        if(studentFound != null) {
            int studentfoundPosition = studentList.indexOf(studentFound);
            studentList.set(studentfoundPosition, student);
        }
    }

    public Student findById(int id) {
        Student studentFound = null;
        for (Student s: studentList) {
            if(s.getId() == id) {
                studentFound = s;
            }
        }
        return studentFound;
    }

    public List<Student> findAll() {
        return studentList;
    }

    public void remove(Student studentToDelete) {
        Student studentFound = findById(studentToDelete.getId());
        if (studentFound != null) studentList.remove(studentFound);
    }
}
