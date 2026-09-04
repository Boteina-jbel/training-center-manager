package org.mql.jee.trainingcenter.tests;

import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingDaoMock implements TrainingDao {

    private List<Training> trainings = new Vector<>();

    @Override
    public List<Training> selectAll() {
        return new Vector<>(trainings);
    }

    @Override
    public Training selectById(int id) {

        for (Training training : trainings) {

            if (training.getId() == id) {
                return training;
            }
        }

        return null;
    }

    @Override
    public void insert(Training training) {
        trainings.add(training);
    }

    @Override
    public void update(Training training) {

        for (int i = 0; i < trainings.size(); i++) {

            if (trainings.get(i).getId() == training.getId()) {

                trainings.set(i, training);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {

        trainings.removeIf(
            training -> training.getId() == id
        );
    }
}