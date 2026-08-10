package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.TrainerService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerAction {

    private TrainerService service;

    public TrainerAction() {
        service = ApplicationContext.getTrainerService();
    }

    // =========================
    // LIST
    // =========================

    public String trainersList(Model model) {

        System.out.println(">> Action : trainersList()");

        List<Trainer> trainers = service.getAllTrainers();

        model.setModel("trainers", trainers);

        return "trainers-list";
    }


    // =========================
    // ADD FORM
    // =========================

    public String trainerAddForm(Model model) {

        System.out.println(">> Action : trainerAddForm()");

        return "trainer-form";
    }


    // =========================
    // ADD
    // =========================

    public String trainerAdd(Model model, String firstName, String lastName, String email, String specialization) {

        System.out.println(">> Action : trainerAdd()");

        Trainer trainer = new Trainer();

        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setEmail(email);
        trainer.setSpecialization(specialization);

        service.addTrainer(trainer);

        return "trainers-list";
    }


    // =========================
    // EDIT FORM
    // =========================

    public String trainerEditForm(Model model, int id) {

        System.out.println(">> Action : trainerEditForm()");

        Trainer trainer = service.getTrainerById(id);

        model.setModel("trainer", trainer);

        return "trainer-form";
    }


    // =========================
    // UPDATE
    // =========================

    public String trainerUpdate(Model model, int id,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String specialization) {

        System.out.println(">> Action : trainerUpdate()");

        Trainer trainer = new Trainer();

        trainer.setId(id);
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setEmail(email);
        trainer.setSpecialization(specialization);

        service.updateTrainer(trainer);

        return "trainers-list";
    }


    // =========================
    // DELETE
    // =========================

    public String trainerDelete(Model model, int id) {

        System.out.println(">> Action : trainerDelete()");

        service.deleteTrainer(id);

        return "trainers-list";
    }
}
