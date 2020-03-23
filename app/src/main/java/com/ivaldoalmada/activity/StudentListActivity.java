package com.ivaldoalmada.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ivaldoalmada.domain.Student;
import com.ivaldoalmada.ui.StudentListView;

public class StudentListActivity extends AppCompatActivity {

    private final String TITLE_APPBAR = "Lista de alunos";
    private final StudentListView studentListView = new StudentListView(this);

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
    }

    private void configureStudentList() {
        ListView listView = findViewById(R.id.activity_main_student_list);
        studentListView.configureAdapter(listView);

        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Student selectedStudent = (Student) parent.getItemAtPosition(position);
                    Log.i("clicked", selectedStudent.toString());
                    Intent openFormIntent = new Intent(StudentListActivity.this, StudentFormActivity.class);
                    openFormIntent.putExtra("selectedStudent", selectedStudent);
                    startActivity(openFormIntent);

                });

        registerForContextMenu(listView);
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
            studentListView.confirmRemoving(item);
        }
        return super.onContextItemSelected(item);
    }
}
