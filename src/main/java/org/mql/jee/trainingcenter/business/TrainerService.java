package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.models.Trainer;

public interface TrainerService {

    List<Trainer> getAllTrainers();

    Trainer getTrainerById(int id);

    void addTrainer(Trainer trainer);

    void updateTrainer(Trainer trainer);

    void deleteTrainer(int id);

}