package com.ivaldoalmada.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import com.ivaldoalmada.activity.adapter.StudentListAdapter;
import com.ivaldoalmada.domain.Student;

public class StudentListView {

    private final StudentListAdapter studentListAdapter;
    private final Context context;

    public StudentListView( Context context) {
        this.context = context;
        this.studentListAdapter = new StudentListAdapter(context);
    }

    public void confirmRemoving(final MenuItem item) {
        new AlertDialog.Builder(context)
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

    public void configureAdapter(ListView studentList) {
        studentList.setAdapter(studentListAdapter);
    }
}
