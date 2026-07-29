package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.TrainerDao;
import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerServiceDefault implements TrainerService {

    // Dependency Inversion
    private TrainerDao trainerDao;

    // Constructor Dependency Injection
    public TrainerServiceDefault(TrainerDao trainerDao) {
        super();
        this.trainerDao = trainerDao;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerDao.selectAll();
    }

    @Override
    public Trainer getTrainerById(int id) {
        return trainerDao.selectById(id);
    }

    @Override
    public void addTrainer(Trainer trainer) {
        trainerDao.insert(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerDao.update(trainer);
    }

    @Override
    public void deleteTrainer(int id) {
        trainerDao.delete(id);
    }

}