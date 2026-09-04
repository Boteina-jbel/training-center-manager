package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.models.Training;

public interface TrainingService {

    List<Training> getAllTrainings();

    Training getTrainingById(int id);

    void addTraining(Training training);

    void updateTraining(Training training);

    void deleteTraining(int id);
}