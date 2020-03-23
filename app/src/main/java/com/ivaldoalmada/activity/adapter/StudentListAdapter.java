package com.ivaldoalmada.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ivaldoalmada.activity.R;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

import java.util.List;

public class StudentListAdapter extends BaseAdapter {

    private final Context context;

    private final StudentDao studentDao = new StudentDao();

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentDao.size();
    }

    @Override
    public Object getItem(int position) {
        return studentDao.findByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return studentDao.findByPosition(position).getId();
    }

    @Override
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        View createdView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
        Student returnedStudent = studentDao.findByPosition(position);
        TextView studentName = createdView.findViewById(R.id.student_item_name);
        studentName.setText(returnedStudent.getName());
        TextView studentPhone = createdView.findViewById(R.id.student_item_phone);
        studentPhone.setText(returnedStudent.getPhone());
        return createdView;

    }

    public void update(List<Student> studentList) {
        studentDao.removeAll();
        studentDao.addAll(studentList);
        notifyDataSetChanged();
    }

    public void remove(Student studentFound) {
        studentDao.remove(studentFound);
        notifyDataSetChanged();
    }



    public List<Student> getAll() {
        return studentDao.findAll();
    }

    public void save(Student student) {
        studentDao.save(student);
    }
}
