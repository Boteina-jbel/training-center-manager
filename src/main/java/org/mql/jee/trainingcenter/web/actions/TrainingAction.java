package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.TrainerService;
import org.mql.jee.trainingcenter.business.TrainingService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Trainer;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingAction {

    private TrainingService service;
    private TrainerService trainerService;

    public TrainingAction() {
        super();

        service = ApplicationContext.getTrainingService();
        trainerService = ApplicationContext.getTrainerService();
    }

    // =====================================================
    // READ - List all trainings
    // =====================================================

    public String trainingsList(Model model) {

        System.out.println(">> Action : trainingsList()");

        List<Training> trainings = service.getAllTrainings();

        model.setModel("trainings", trainings);

        return "trainings-list";
    }

    // =====================================================
    // CREATE - Show add form
    // =====================================================

    public String trainingAddForm(Model model) {

        System.out.println(">> Action : trainingAddForm()");

        List<Trainer> trainers = trainerService.getAllTrainers();

        model.setModel("trainers", trainers);

        return "training-form";
    }

    // =====================================================
    // CREATE - Add training
    // =====================================================

    public String addTraining(
            Training training,
            Model model) {

        System.out.println(">> Action : addTraining()");

        service.addTraining(training);

        return trainingsList(model);
    }

    // =====================================================
    // UPDATE - Show edit form
    // =====================================================

    public String trainingEditForm(
            int id,
            Model model) {

        System.out.println(">> Action : trainingEditForm()");

        Training training = service.getTrainingById(id);

        List<Trainer> trainers = trainerService.getAllTrainers();

        model.setModel("training", training);
        model.setModel("trainers", trainers);

        return "training-form";
    }

    // =====================================================
    // UPDATE - Update training
    // =====================================================

    public String updateTraining(
            Training training,
            Model model) {

        System.out.println(">> Action : updateTraining()");

        service.updateTraining(training);

        return trainingsList(model);
    }

    // =====================================================
    // DELETE - Delete training
    // =====================================================

    public String deleteTraining(
            int id,
            Model model) {

        System.out.println(">> Action : deleteTraining()");

        service.deleteTraining(id);

        return trainingsList(model);
    }
}