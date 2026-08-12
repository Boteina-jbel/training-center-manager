package org.mql.jee.trainingcenter.tests;

import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.dao.TrainerDao;
import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerDaoMock implements TrainerDao {

    private List<Trainer> trainers = new Vector<>();

    @Override
    public List<Trainer> selectAll() {
        return new Vector<>(trainers);
    }

    @Override
    public Trainer selectById(int id) {

        for (Trainer trainer : trainers) {

            if (trainer.getId() == id) {
                return trainer;
            }
        }

        return null;
    }

    @Override
    public void insert(Trainer trainer) {
        trainers.add(trainer);
    }

    @Override
    public void update(Trainer trainer) {

        for (int i = 0; i < trainers.size(); i++) {

            if (trainers.get(i).getId() == trainer.getId()) {

                trainers.set(i, trainer);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {

        trainers.removeIf(
            trainer -> trainer.getId() == id
        );
    }
}