package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.jdbc.Database;
import org.mql.jee.trainingcenter.dao.mappers.TrainerORM;
import org.mql.jee.trainingcenter.models.Trainer;

public class TrainerDaoJdbc implements TrainerDao{
	
	private String tableName = "trainers";
	private Database db;
	
	public TrainerDaoJdbc(Database db) {
		this.db = db;
	}

	@Override
	public List<Trainer> selectAll() {
		String data[][] = db.select(tableName);
        return TrainerORM.getTrainersList(data);
	}

	@Override
	public Trainer selectById(int id) {

	    String[][] data = db.selectById(tableName, "id", id);

	    if (data == null || data.length == 0) {
	        return null;
	    }

	    return TrainerORM.getTrainer(data[0]);
	}

	@Override
	public void insert(Trainer trainer) {

	    String query =
	        "INSERT INTO trainers(first_name,last_name,email,specialization) VALUES(" +
	        "'" + trainer.getFirstName() + "'," +
	        "'" + trainer.getLastName() + "'," +
	        "'" + trainer.getEmail() + "'," +
	        "'" + trainer.getSpecialization() + "')";

	    db.executeUpdate(query);
	}

	@Override
	public void update(Trainer trainer) {

	    String query =
	        "UPDATE trainers SET " +
	        "first_name='" + trainer.getFirstName() + "'," +
	        "last_name='" + trainer.getLastName() + "'," +
	        "email='" + trainer.getEmail() + "'," +
	        "specialization='" + trainer.getSpecialization() + "'" +
	        " WHERE id=" + trainer.getId();

	    db.executeUpdate(query);
	}

	@Override
	public void delete(int id) {

	    String query =
	        "DELETE FROM trainers WHERE id=" + id;

	    db.executeUpdate(query);
	}


}
