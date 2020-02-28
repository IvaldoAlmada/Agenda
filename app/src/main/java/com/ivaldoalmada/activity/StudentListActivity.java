package com.ivaldoalmada.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.ivaldoalmada.activity.adapter.StudentListAdapter;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private final String TITLE_APPBAR = "Lista de alunos";
    private StudentListAdapter studentListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        configureFabNewStudent();
        configureStudentList();
    }

    private void configureFabNewStudent() {
        FloatingActionButton addStudentButton = findViewById(R.id.activity_main_fab_student_list);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormActivity();
            }
        });
    }

    private void openFormActivity() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentListAdapter.update(new ArrayList<>(studentListAdapter.getAll()));
    }

    private void configureStudentList() {
        ListView studentListView = findViewById(R.id.activity_main_student_list);
        studentListAdapter = new StudentListAdapter(this);

        studentListView.setAdapter(studentListAdapter);

        studentListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = (Student) parent.getItemAtPosition(position);
                Log.i("clicked", selectedStudent.toString());
                Intent openFormIntent = new Intent(StudentListActivity.this, StudentFormActivity.class);
                openFormIntent.putExtra("selectedStudent", selectedStudent);
                startActivity(openFormIntent);

            }
        });

        registerForContextMenu(studentListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Remove Student")
                    .setMessage("Tem certeza que deseja remover o aluno?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Student studentFound = (Student) studentListAdapter.getItem(menuInfo.position);
                            studentListAdapter.remove(studentFound);
                        }
                    })
                    .setNegativeButton("Nao", null)
                    .show();


        }
        return super.onContextItemSelected(item);
    }
}
