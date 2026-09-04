package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.jdbc.Database;
import org.mql.jee.trainingcenter.dao.mappers.TrainingORM;
import org.mql.jee.trainingcenter.models.Training;

public class TrainingDaoJdbc implements TrainingDao {

    private String tableName = "trainings";
    private Database db;

    public TrainingDaoJdbc(Database db) {
        this.db = db;
    }

    @Override
    public List<Training> selectAll() {

        String data[][] = db.select(tableName);

        return TrainingORM.getTrainingsList(data);
    }

    @Override
    public Training selectById(int id) {

        String[][] data =
                db.selectById(tableName, "id", id);

        if (data == null || data.length == 0) {
            return null;
        }

        return TrainingORM.getTraining(data[0]);
    }

    @Override
    public void insert(Training training) {

        db.executeUpdate(
            "INSERT INTO trainings"
            + "(title,description,duration,trainer_id) VALUES('"
            + training.getTitle() + "','"
            + training.getDescription() + "',"
            + training.getDuration() + ","
            + training.getTrainer().getId()
            + ")"
        );
    }

    @Override
    public void update(Training training) {

        db.executeUpdate(
            "UPDATE trainings SET "
            + "title='" + training.getTitle() + "', "
            + "description='" + training.getDescription() + "', "
            + "duration=" + training.getDuration() + ", "
            + "trainer_id=" + training.getTrainer().getId()
            + " WHERE id=" + training.getId()
        );
    }

    @Override
    public void delete(int id) {

        db.executeUpdate(
            "DELETE FROM trainings WHERE id=" + id
        );
    }
}