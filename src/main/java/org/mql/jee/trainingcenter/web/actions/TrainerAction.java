package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.TrainerService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerAction {

    private TrainerService service;

    public TrainerAction() {
        super();
        service = ApplicationContext.getTrainerService();
    }

    // READ - List all trainers
    public String trainersList(Model model) {

        System.out.println(">> Action : trainersList()");

        List<Trainer> trainers = service.getAllTrainers();

        model.setModel("trainers", trainers);

        return "trainers-list";
    }

    // CREATE - Show add form
    public String trainerAddForm(Model model) {

        System.out.println(">> Action : trainerAddForm()");

        return "trainer-form";
    }

    // CREATE - Add trainer
    public String addTrainer(Trainer trainer, Model model) {

        System.out.println(">> Action : trainerAdd()");

        service.addTrainer(trainer);

        return trainersList(model);
    }

    // UPDATE - Show edit form
    public String trainerEditForm(int id, Model model) {

        System.out.println(">> Action : trainerEditForm()");

        Trainer trainer = service.getTrainerById(id);

        model.setModel("trainer", trainer);

        return "trainer-form";
    }

    // UPDATE - Update trainer
    public String updateTrainer(Trainer trainer, Model model) {

        System.out.println(">> Action : trainerUpdate()");

        service.updateTrainer(trainer);

        return trainersList(model);
    }

    // DELETE - Delete trainer
    public String deleteTrainer(int id, Model model) {

        System.out.println(">> Action : trainerDelete()");

        service.deleteTrainer(id);

        return trainersList(model);
    }
}
