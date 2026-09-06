package org.mql.jee.trainingcenter.context;

import org.mql.jee.jdbc.DataSource;
import org.mql.jee.jdbc.Database;
import org.mql.jee.jdbc.MySQLDataSource;

import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.business.StudentServiceDefault;
import org.mql.jee.trainingcenter.business.TrainerService;
import org.mql.jee.trainingcenter.business.TrainerServiceDefault;
import org.mql.jee.trainingcenter.business.TrainingService;
import org.mql.jee.trainingcenter.business.TrainingServiceDefault;

import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.dao.StudentDaoJdbc;
import org.mql.jee.trainingcenter.dao.TrainerDao;
import org.mql.jee.trainingcenter.dao.TrainerDaoJdbc;
import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.dao.TrainingDaoJdbc;

public class ApplicationContext {

    private static StudentService studentService;
    private static StudentDao studentDao;

    private static TrainerService trainerService;
    private static TrainerDao trainerDao;

    private static TrainingService trainingService;
    private static TrainingDao trainingDao;

    static {

        // Wiring
        DataSource ds = new MySQLDataSource(
                "training_center",
                "root",
                "root"
        );

        Database db = new Database(ds);

        // STUDENT
        studentDao = new StudentDaoJdbc(db);
        studentService = new StudentServiceDefault(studentDao);

        // TRAINER
        trainerDao = new TrainerDaoJdbc(db);
        trainerService = new TrainerServiceDefault(trainerDao);

        // TRAINING
        trainingDao = new TrainingDaoJdbc(db);
        trainingService = new TrainingServiceDefault(trainingDao);
    }

    // STUDENT
    public static StudentService getStudentService() {
        return studentService;
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }

    // TRAINER
    public static TrainerService getTrainerService() {
        return trainerService;
    }

    public static TrainerDao getTrainerDao() {
        return trainerDao;
    }

    // TRAINING
    public static TrainingService getTrainingService() {
        return trainingService;
    }

    public static TrainingDao getTrainingDao() {
        return trainingDao;
    }
}