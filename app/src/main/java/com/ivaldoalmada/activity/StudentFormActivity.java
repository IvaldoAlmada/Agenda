package com.ivaldoalmada.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

public class StudentFormActivity extends AppCompatActivity {

    private final StudentDao studentDao = new StudentDao();

    private final String TITLE_APPBAR = "Cadastrar alunos";

    private EditText studentName;
    private EditText studentPhone;
    private EditText studentEmail;

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle(TITLE_APPBAR);

        initializeStudentAttributes();
        configureSaveButtom();
        Intent intent = getIntent();

        if (intent.hasExtra("selectedStudent")) {
            student = (Student) intent.getSerializableExtra("selectedStudent");
            studentName.setText(student.getName());
            studentEmail.setText(student.getEmail());
            studentPhone.setText(student.getPhone());
        } else {
            student = new Student();
        }
    }

    private void configureSaveButtom() {
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillStudent();
                if(student.validId()) {
                    studentDao.edit(student);
                } else {
                    studentDao.save(student);
                }

                finish();
            }
        });
    }

    private void fillStudent() {
        String name = studentName.getText().toString();
        String phone = studentPhone.getText().toString();
        String email = studentEmail.getText().toString();
        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }

    private void initializeStudentAttributes() {
        studentName = findViewById(R.id.formStudentName);
        studentPhone = findViewById(R.id.formStudentPhone);
        studentEmail = findViewById(R.id.formStudentEmail);
    }

}
