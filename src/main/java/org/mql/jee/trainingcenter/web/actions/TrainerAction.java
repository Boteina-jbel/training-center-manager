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

    // Action : afficher la liste des formateurs
    public String trainersList(Model model) {

        System.out.println(">> Action : trainersList()");

        // 1. Récupérer les données depuis la couche métier
        List<Trainer> trainers = service.getAllTrainers();

        // 2. Les placer dans le modèle
        model.setModel("trainers", trainers);

        // 3. Retourner le nom de la vue
        return "trainers-list";
    }
}