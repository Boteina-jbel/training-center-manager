package org.mql.jee.trainingcenter.dao.mappers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerORM {

    public static Trainer getTrainer(String... row) {

        Trainer trainer = new Trainer();

        trainer.setId(getInt(row[0]));
        trainer.setFirstName(row[1]);
        trainer.setLastName(row[2]);
        trainer.setEmail(row[3]);
        trainer.setSpecialization(row[4]);

        if (row.length > 5 && row[5] != null) {
            trainer.setCreatedAt(Timestamp.valueOf(row[5]));
        }

        return trainer;
    }

    public static List<Trainer> getTrainersList(String[][] data) {

        List<Trainer> trainers = new Vector<>();

        if (data == null) {
            return trainers;
        }

        for (String[] row : data) {
            trainers.add(getTrainer(row));
        }

        return trainers;
    }

    public static int getInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println("Mapping Error : " + e.getMessage());
            return -1;
        }
    }
}