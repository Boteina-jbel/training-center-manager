package org.mql.jee.trainingcenter.dao.mappers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.models.Trainer;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingORM {

    public static Training getTraining(String... row) {

        Training training = new Training();

        training.setId(getInt(row[0]));
        training.setTitle(row[1]);
        training.setDescription(row[2]);
        training.setDuration(getInt(row[3]));

        // Trainer
        if (row.length > 4 && row[4] != null) {

            Trainer trainer = new Trainer();

            trainer.setId(getInt(row[4]));

            training.setTrainer(trainer);
        }

        // CreatedAt
        if (row.length > 5 && row[5] != null) {
            training.setCreatedAt(
                Timestamp.valueOf(row[5])
            );
        }

        return training;
    }

    public static List<Training> getTrainingsList(String[][] data) {

        List<Training> trainings = new Vector<Training>();

        for (String[] row : data) {
            trainings.add(getTraining(row));
        }

        return trainings;
    }

    public static int getInt(String data) {

        try {
            return Integer.parseInt(data);

        } catch (Exception e) {

            System.out.println(
                "Mapping Error : " + e.getMessage()
            );

            return -1;
        }
    }
}