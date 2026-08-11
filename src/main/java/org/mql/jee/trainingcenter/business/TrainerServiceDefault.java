package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.TrainerDao;
import org.mql.jee.trainingcenter.exceptions.TrainerException;
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

        if (id <= 0) {
            throw new TrainerException("Invalid trainer ID.");
        }

        Trainer trainer = trainerDao.selectById(id);

        if (trainer == null) {
            throw new TrainerException("Trainer not found.");
        }

        return trainer;
    }

    @Override
    public void addTrainer(Trainer trainer) {

        validateTrainer(trainer);

        trainerDao.insert(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {

        validateTrainer(trainer);

        if (trainer.getId() <= 0) {
            throw new TrainerException("Invalid trainer ID.");
        }

        Trainer existingTrainer =
                trainerDao.selectById(trainer.getId());

        if (existingTrainer == null) {
            throw new TrainerException("Trainer not found.");
        }

        trainerDao.update(trainer);
    }

    @Override
    public void deleteTrainer(int id) {

        if (id <= 0) {
            throw new TrainerException("Invalid trainer ID.");
        }

        Trainer trainer = trainerDao.selectById(id);

        if (trainer == null) {
            throw new TrainerException("Trainer not found.");
        }

        trainerDao.delete(id);
    }

    // =========================
    // VALIDATION
    // =========================

    private void validateTrainer(Trainer trainer) {

        if (trainer == null) {
            throw new TrainerException("Trainer cannot be null.");
        }

        if (isEmpty(trainer.getFirstName())) {
            throw new TrainerException("First name is required.");
        }

        if (isEmpty(trainer.getLastName())) {
            throw new TrainerException("Last name is required.");
        }

        if (isEmpty(trainer.getEmail())) {
            throw new TrainerException("Email is required.");
        }

        if (!isValidEmail(trainer.getEmail())) {
            throw new TrainerException("Invalid email format.");
        }

        if (isEmpty(trainer.getSpecialization())) {
            throw new TrainerException("Specialization is required.");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}
