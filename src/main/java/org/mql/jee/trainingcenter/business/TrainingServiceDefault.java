package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.exceptions.TrainingException;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingServiceDefault implements TrainingService {

    private TrainingDao trainingDao;

    public TrainingServiceDefault(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDao.selectAll();
    }

    @Override
    public Training getTrainingById(int id) {

        if (id <= 0) {
            throw new TrainingException("Invalid training ID.");
        }

        Training training = trainingDao.selectById(id);

        if (training == null) {
            throw new TrainingException("Training not found.");
        }

        return training;
    }

    @Override
    public void addTraining(Training training) {

        validateTraining(training);

        trainingDao.insert(training);
    }

    @Override
    public void updateTraining(Training training) {

        validateTraining(training);

        if (training.getId() <= 0) {
            throw new TrainingException("Invalid training ID.");
        }

        Training existingTraining =
                trainingDao.selectById(training.getId());

        if (existingTraining == null) {
            throw new TrainingException("Training not found.");
        }

        trainingDao.update(training);
    }

    @Override
    public void deleteTraining(int id) {

        if (id <= 0) {
            throw new TrainingException("Invalid training ID.");
        }

        Training training = trainingDao.selectById(id);

        if (training == null) {
            throw new TrainingException("Training not found.");
        }

        trainingDao.delete(id);
    }

    private void validateTraining(Training training) {

        if (training == null) {
            throw new TrainingException("Training cannot be null.");
        }

        if (isEmpty(training.getTitle())) {
            throw new TrainingException("Title is required.");
        }

        if (isEmpty(training.getDescription())) {
            throw new TrainingException("Description is required.");
        }

        if (training.getDuration() <= 0) {
            throw new TrainingException("Duration must be greater than 0.");
        }

        if (training.getTrainer() == null) {
            throw new TrainingException("Trainer is required.");
        }

        if (training.getTrainer().getId() <= 0) {
            throw new TrainingException("Invalid trainer ID.");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}