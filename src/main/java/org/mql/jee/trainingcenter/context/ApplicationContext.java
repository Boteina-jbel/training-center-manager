package org.mql.jee.trainingcenter.context;

import org.mql.jee.jdbc.DataSource;
import org.mql.jee.jdbc.Database;
import org.mql.jee.jdbc.MySQLDataSource;
import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.business.StudentServiceDefault;
import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.dao.StudentDaoJdbc;

public class ApplicationContext {

    private static StudentService studentService;
    private static StudentDao studentDao;

    static {

        // Wiring
        DataSource ds =
                new MySQLDataSource(
                        "training_center",
                        "root",
                        "root"
                );

        Database db = new Database(ds);
        studentDao = new StudentDaoJdbc(db);
        studentService = new StudentServiceDefault(studentDao);
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }
}
