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
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private final StudentDao studentDao = new StudentDao();
    private final String TITLE_APPBAR = "Lista de alunos";
    private Intent openFormIntent;
    private ArrayAdapter<Student> studentListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        studentDao.save(new Student("Ivaldo", "987445311", "ivaldoalmada@gmail.com"));
        studentDao.save(new Student("Ju", "989218286", "ju@gmail.com"));
        configureStudentList();
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
        studentListAdapter.clear();
        studentListAdapter.addAll(studentDao.findAll());

    }

    private void configureStudentList() {
        ListView studentListView = findViewById(R.id.activity_main_student_list);
        studentListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        studentListView.setAdapter(studentListAdapter);

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = (Student) parent.getItemAtPosition(position);
                Log.i("clicked", selectedStudent.toString());
                openFormIntent.putExtra("selectedStudent", selectedStudent);
                startActivity(openFormIntent);

            }
        });

        studentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentToDelete = (Student) parent.getItemAtPosition(position);
                studentDao.remove(studentToDelete);
                studentListAdapter.remove(studentToDelete);
                return true;
            }
        });
    }
}
