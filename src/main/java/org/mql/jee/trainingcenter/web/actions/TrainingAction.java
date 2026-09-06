package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.TrainingService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingAction {

    private TrainingService service;

    public TrainingAction() {
        super();
        service = ApplicationContext.getTrainingService();
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

        return "training-form";
    }

    // =====================================================
    // CREATE - Add training
    // =====================================================

    public String addTraining(Training training, Model model) {

        System.out.println(">> Action : addTraining()");

        service.addTraining(training);

        return trainingsList(model);
    }

    // =====================================================
    // UPDATE - Show edit form
    // =====================================================

    public String trainingEditForm(int id, Model model) {

        System.out.println(">> Action : trainingEditForm()");

        Training training = service.getTrainingById(id);

        model.setModel("training", training);

        return "training-form";
    }

    // =====================================================
    // UPDATE - Update training
    // =====================================================

    public String updateTraining(Training training, Model model) {

        System.out.println(">> Action : updateTraining()");

        service.updateTraining(training);

        return trainingsList(model);
    }

    // =====================================================
    // DELETE - Delete training
    // =====================================================

    public String deleteTraining(int id, Model model) {

        System.out.println(">> Action : deleteTraining()");

        service.deleteTraining(id);

        return trainingsList(model);
    }
}