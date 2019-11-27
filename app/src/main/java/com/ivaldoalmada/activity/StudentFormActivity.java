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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle(TITLE_APPBAR);

        initializeStudentAttributes();
        configureSaveButtom();
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("selectedStudent");
        studentName.setText(student.getName());
        studentEmail.setText(student.getEmail());
        studentPhone.setText(student.getPhone());
    }

    private void configureSaveButtom() {
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                saveStudent(student);
            }
        });
    }

    private void saveStudent(Student student) {
        studentDao.save(student);
        finish();
    }

    private Student createStudent() {
        return new Student(studentName.getText().toString(), studentPhone.getText().toString(),
                studentEmail.getText().toString());
    }

    private void initializeStudentAttributes() {
        studentName = findViewById(R.id.formStudentName);
        studentPhone = findViewById(R.id.formStudentPhone);
        studentEmail = findViewById(R.id.formStudentEmail);
    }

}
