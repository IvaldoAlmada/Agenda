package com.ivaldoalmada.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private final StudentDao studentDao = new StudentDao();
    private final String TITLE_APPBAR = "Lista de alunos";
    private Intent openFormIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        studentDao.save(new Student("Ivaldo", "987445311", "ivaldoalmada@gmail.com"));
        studentDao.save(new Student("Ju", "989218286", "ju@gmail.com"));
        configureFabNewStudent();
    }

    private void configureFabNewStudent() {
        openFormIntent = new Intent(this, StudentFormActivity.class);
        FloatingActionButton addStudentButton = findViewById(R.id.activity_main_fab_student_list);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormActivity();
            }
        });
    }

    private void openFormActivity() {
        startActivity(openFormIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureStudentList();
    }

    private void configureStudentList() {
        final List<Student> studentList = studentDao.findAll();
        ListView studentListView = findViewById(R.id.activity_main_student_list);
        studentListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                studentList));

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = studentList.get(position);
                Log.i("clicked", selectedStudent.toString());
                openFormIntent.putExtra("selectedStudent", selectedStudent);
                startActivity(openFormIntent);

            }
        });
    }
}
