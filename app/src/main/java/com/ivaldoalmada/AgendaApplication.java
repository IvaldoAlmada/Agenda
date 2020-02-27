package com.ivaldoalmada;

import android.app.Application;
import com.ivaldoalmada.dao.StudentDao;
import com.ivaldoalmada.domain.Student;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StudentDao studentDao = new StudentDao();

        studentDao.save(new Student("Ivaldo", "987445311", "ivaldoalmada@gmail.com"));
        studentDao.save(new Student("Ju", "989218286", "ju@gmail.com"));
    }
}
